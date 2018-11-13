package com.traskindustries;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ApplicationTests {

	//@Test
	//public void contextLoads() {
	//}

	static class Foo {
		private final int id;

		private final String s;

		public Foo(int id, String s) {
			super();
			this.id = id;
			this.s = s;
		}

		public int getId() {
			return id;
		}

		public String getS() {
			return s;
		}

		@Override
		public String toString() {
			return "" + id;
		}
	}

	@Test
	public void test() {

		HashMap<String, Foo> result =
				Arrays.asList(new Foo(1, "a"), new Foo(2, "b"))
				.stream()
				.collect(HashMap::new,
						(map, foo) -> map.put(foo.getS(), foo),
						HashMap::putAll);

		System.out.println(result); // {a=1, b=2}
	}

}
