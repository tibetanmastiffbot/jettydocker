package com.me.jackson.generics;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JacksonGeneric {

	public static void main(String[] args) throws Exception {

		Dog dog = new Dog();
		dog.setAge(10);
		dog.setName("Tibetan");
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(dog));

		String serialized = "{`name`:`Tibetan`,`age`:10}".replace('`', '"');
		Dog dogClone = JacksonGeneric.deserialize(serialized, Dog.class);
		System.out.println(dogClone);

		Bear bear = new Bear();
		bear.setAge(99);
		bear.setName("Mastiff");
		bear.setFurColor(83);
		System.out.println(mapper.writeValueAsString(bear));

		serialized = "{`name`:`Mastiff`,`furColor`:83,`age`:99}".replace('`', '"');
		Bear bearClone = JacksonGeneric.deserialize(serialized, Bear.class);
		System.out.println(bearClone);
	}

	static <T> T deserialize(String serialized, Class<T> theClass)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		return mapper.readValue(serialized, theClass);
	}
}
