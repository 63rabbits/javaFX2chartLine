package javaFX2chartLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class OpCsv {

	private URL url;
	private TreeMap<Integer, String[]> csv = new TreeMap<>();

	public OpCsv(URL url) {
		this.url = url;
		readCsvFile();
	}

	public URL getUrl() {
		return url;
	}

	public String getCsvFileName() {
		return url.toString();
	}

	public int size() {
		return csv.size();
	}

	public TreeMap<Integer, String[]> getCsv() {
		return csv;
	}

	public TreeMap<Integer, String[]> getSortedCsv(int sortColumn) {
		return sortCsv(sortColumn, 0);
	}

	public TreeMap<Integer, String[]> getReverseCsv(int sortColumn) {
		return sortCsv(sortColumn, 1);
	}

	public TreeMap<Integer, String[]> getNumberSortedCsv(int sortColumn) {
		return sortCsv(sortColumn, 2);
	}

	public TreeMap<Integer, String[]> getNumberReverseCsv(int sortColumn) {
		return sortCsv(sortColumn, 3);
	}

	private TreeMap<Integer, String[]> sortCsv(int sortColumn, int order) {
		final int c = sortColumn;
		ArrayList<String[]> aList = new ArrayList<>();
		Iterator<Integer> it = csv.keySet().iterator();
		while (it.hasNext()) {
			int no = it.next();
			aList.add(csv.get(no));
		}

		switch (order) {
		case 0:
			Collections.sort(aList, new Comparator<String[]>() {
				public int compare(String[] d1, String[] d2) {
					return d1[c].compareTo(d2[c]);
				}
			});
			break;
		case 1:
			Collections.sort(aList, new Comparator<String[]>() {
				public int compare(String[] d1, String[] d2) {
					return d2[c].compareTo(d1[c]);
				}
			});
			break;
		case 2:
			Collections.sort(aList, new Comparator<String[]>() {
				public int compare(String[] d1, String[] d2) {
					Double dd1 = Double.parseDouble(d1[c]);
					Double dd2 = Double.parseDouble(d2[c]);
					return dd1.compareTo(dd2);
				}
			});
			break;
		case 3:
			Collections.sort(aList, new Comparator<String[]>() {
				public int compare(String[] d1, String[] d2) {
					Double dd1 = Double.parseDouble(d1[c]);
					Double dd2 = Double.parseDouble(d2[c]);
					return dd2.compareTo(dd1);
				}
			});
			break;
		}

		TreeMap<Integer, String[]> scsv = new TreeMap<>();
		for (int i = 0; i < aList.size(); i++) {
			scsv.put(i, aList.get(i));
		}

		return scsv;
	}

	private void readCsvFile() {

		try {
			InputStream is = url.openStream();
			//			BufferedReader br = new BufferedReader(new InputStreamReader(is, "Shift-JIS"));
			//			BufferedReader br = new BufferedReader(new InputStreamReader(is, "JISAutoDetect"));
			//			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line = "";
			int lineNo = 0;
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ",");
				String[] words = new String[st.countTokens()];
				for (int i = 0; st.hasMoreTokens(); i++) {
					words[i] = st.nextToken();
				}
				csv.put(lineNo, words);
				lineNo++;
			}
			br.close();
			is.close();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
