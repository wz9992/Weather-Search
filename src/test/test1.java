package test;
import java.util.ArrayList;
import java.util.Scanner;

public class test1 {

	private ArrayList<Word> wordList;

	public static void main(String[] args) {

		test1 test1 = new test1();
		// ���뵥���б�
		test1.inputWordList();
		// ʶ�𵥴��б�
		for (Word word : test1.wordList) {
			// ʶ�𵥴�
			test1.whichType(word);
			String temp = word.getCJ2();
			if (temp != null) {
				int index = temp.lastIndexOf(".0");
				if (index == temp.length() - 2)
					word.setCJ2(temp.substring(0, index));
			}
		}
		// ��������б�
		test1.printWordList();
	}

	// ʶ�𵥴�
	private void whichType(Word word) {
		int w = 0;
		int p = 0;
		int j = 0;
		int e = 1;
		int d = 0;
		String chars[] = word.getStr().split("");
		// �ӵ�һ���ַ���ʼ
		int i = 0;
		String current_char = chars[i];
		// ���ַ�?
		if (!isNum(current_char)) {
			word.setCJ1("����");
			return;
		} else {
			while (true) {
				d = Integer.parseInt(current_char);
				w = w * 10 + d;
				current_char = chars[++i];
				// ���ַ�?
				if (isNum(current_char)) {
					continue;
				} else {
					// ��'.'��?
					if (current_char.equals(".")) {
						current_char = chars[++i];
						// ���ַ�?
						if (!isNum(current_char)) {
							word.setCJ1("����");
							return;
						} else {
							while (true) {
								d = Integer.parseInt(current_char);
								w = w * 10 + d;
								j = j + 1;
								current_char = chars[++i];
								// ���ַ�?
								if (isNum(current_char)) {
									continue;
								} else {
									// ��'E'��?
									if (current_char.equals("E")) {
										isE(current_char, chars, i, word, w, p, j, e, d);
										return;
									} else {
										// ��һ�ַ�
										word.setCJ1("ʵ��");
										word.setCJ2((w * Math.pow(10, e * p - j)) + "");
										return;
									}
								}
							}
						}
					} else {
						// ��'E'��?
						if (current_char.equals("E")) {
							isE(current_char, chars, i, word, w, p, j, e, d);
							return;
						} else {
							// ��һ�ַ�
							word.setCJ1("����");
							word.setCJ2((w * Math.pow(10, e * p - j)) + "");
							return;
						}
					}
				}
			}
		}
	}
	private void isE(String current_char, String[] chars, int i, Word word, int w, int p, int j, int e, int d) {

		current_char = chars[++i];
		// ��'-'��?
		if (current_char.equals("-")) {
			e = -1;
			current_char = chars[++i];
			// ���ַ�?
			if (isNum(current_char)) {
				while (true) {
					d = Integer.parseInt(current_char);
					p = p * 10 + d;
					current_char = chars[++i];
					if (isNum(current_char)) {
						continue;
					} else {
						// ��һ�ַ�
						word.setCJ1("ʵ��");
						word.setCJ2((w * Math.pow(10, e * p - j)) + "");
						return;
					}
				}
			} else {
				word.setCJ1("����");
				return;
			}
		} else {
			// ��'+'��?
			if (current_char.equals("+")) {
				current_char = chars[++i];
				// ���ַ�?
				if (isNum(current_char)) {
					while (true) {
						d = Integer.parseInt(current_char);
						p = p * 10 + d;
						current_char = chars[++i];
						if (isNum(current_char)) {
							continue;
						} else {
							// ��һ�ַ�
							word.setCJ1("ʵ��");
							word.setCJ2((w * Math.pow(10, e * p - j)) + "");
							return;
						}
					}
				} else {
					word.setCJ1("����");
					return;
				}
			} else {
				// ���ַ�?
				if (isNum(current_char)) {
					while (true) {
						d = Integer.parseInt(current_char);
						p = p * 10 + d;
						current_char = chars[++i];
						if (isNum(current_char)) {
							continue;
						} else {
							// ��һ�ַ�
							word.setCJ1("ʵ��");
							word.setCJ2((w * Math.pow(10, e * p - j)) + "");
							return;
						}
					}
				} else {
					word.setCJ1("����");
					return;
				}
			}
		}
	}
	// �Ƿ�Ϊ����
	private boolean isNum(String string) {

		return "0123456789".contains(string);
	}
	// ���뵥���б�
	private void inputWordList() {

		System.out.println("������һ���ַ��������ʼ��ÿո��������β��Ӣ�ġ�;��");
		Scanner scanner = new Scanner(System.in);
		String temp = scanner.nextLine().replace(";", "");
		scanner.close();
		String[] strs = temp.split(" ");
		wordList = new ArrayList<Word>();
		for (String str : strs) {
			Word word = new Word();
			word.setStr(str + "#");
			wordList.add(word);
		}
	}

	// ��������б�
	private void printWordList() {

		for (Word word : wordList) {
			System.out.println(word.getStr() + "\t" + word.getCJ2() + "\t" + word.getCJ1());
		}
	}

	// ������
	private class Word {
		// str��ԭʼ����
		private String str;
		// CJ1������
		private String CJ1;
		// CJ1����ֵ
		private String CJ2;

		public String getStr() {
			return str;
		}

		public void setStr(String str) {
			this.str = str;
		}

		public String getCJ1() {
			return CJ1;
		}

		public void setCJ1(String cJ1) {
			CJ1 = cJ1;
		}

		public String getCJ2() {
			return CJ2;
		}

		public void setCJ2(String cJ2) {
			CJ2 = cJ2;
		}
	}
}