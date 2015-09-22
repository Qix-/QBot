package qix.qbot.test;

import static org.junit.Assert.assertArrayEquals;

import java.util.Collection;

public final class TestUtils {
	private TestUtils(){}
	
	@SafeVarargs
	public static <E> void assertCollectionEquals(Collection<E> collection, E... items) {
		Object[] array = collection.toArray();
		assertArrayEquals(items, array);
	}
}
