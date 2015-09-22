package qix.qbot.util;

import java.util.Comparator;

/**
 * Pretty much my same implementation I had before, but thanks
 * to Carl Manaster and PFA over at
 * stackoverflow.com/questions/1261934/nice-general-way-to-sort-nulls-to-the-bottom-regardless
 * 
 * @author qix
 * @author Carl Manaster
 * @author PFA
 *
 */
public class NullComparators {
	public static <T> Comparator<T> atEnd(final Comparator<T> comparator) {
		return new Comparator<T>() {
			public int compare(T a, T b) {
				if (a == null && b == null)
					return 0;
				if (a == null)
					return 1;
				if (b == null)
					return -1;
				return comparator.compare(a, b);
			}
		};
	}

	public static <T> Comparator<T> atBeginning(final Comparator<T> comparator) {
		return new Comparator<T>() {
			public int compare(T a, T b) {
				if (a == null && b == null)
					return 0;
				if (a == null)
					return -1;
				if (b == null)
					return 1;
				return comparator.compare(a, b);
			}
		};
	}
}
