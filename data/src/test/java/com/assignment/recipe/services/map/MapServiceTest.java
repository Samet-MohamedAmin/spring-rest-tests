package com.assignment.recipe.services.map;


import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import com.assignment.recipe.models.BaseEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.Data;

public class MapServiceTest {

	private MapService<TestObject, Long> mapService;

	private Set<TestObject> objectList;

	private final int testObjectsNumber = 3;

	@Data
	private class TestObject implements BaseEntity {

		private Long id;
	}

	private void setUpTestObjectList() {

		objectList = new HashSet<TestObject>();
		
		for (int i = 1; i <= testObjectsNumber; i++) {
			TestObject testObject = new TestObject();
			testObject.setId(Long.valueOf(i));
			objectList.add(testObject);
		}
	}

	@BeforeEach
	public void setUp() throws Exception {

		mapService = new MapService<>();

		setUpTestObjectList();
	}

	@Test
	public void testFindAll() throws Exception {

		mapService.saveAll(objectList);
		int actualTestObjectsNumber = mapService.findAll().size();
		assertEquals(testObjectsNumber, actualTestObjectsNumber);
	}

	@Test
	public void testFindById() throws Exception {

		mapService.saveAll(objectList);
		for (TestObject object : objectList) {
			
			TestObject testObjectFound = mapService.findById(object.getId());
			assertNotNull(testObjectFound);
			assertEquals(object, testObjectFound);
		}
	}

	@Test
	public void testSave() throws Exception {

		objectList.forEach(mapService::save);
		assertEquals(testObjectsNumber, mapService.findAll().size());
	}

	@Test
	public void testSaveNullObject() throws Exception {

		assertThrows(NullPointerException.class,
			() -> mapService.save(null),
			"It should throw NullPointerException when saving null object");
	}

	@Test
	public void testSaveNullId() throws Exception {

		for (int i = 0; i < testObjectsNumber; i++) {

			TestObject objectNullId = new TestObject();
			objectNullId.setId(null);
			mapService.save(objectNullId);
		}

		var objectList = mapService.findAll();

		// be sure that there is no null value as id for any object
		for (var object : objectList) {
			assertNotNull(object.getId());
		}

		// be sure that all the objects were saved
		int expectedSize = testObjectsNumber;
		int actualSize = objectList.size();
		assertEquals(expectedSize, actualSize);
	}

	@Test
	public void testSaveZeroId() throws Exception {

		for (int i = 0; i < testObjectsNumber; i++) {

			TestObject objectZeroId = new TestObject();
			objectZeroId.setId(0L);
			mapService.save(objectZeroId);
		}

		var objectList = mapService.findAll();

		// be sure that there is no 0L as id for any object
		for (var object : objectList) {
			assertNotEquals(Long.valueOf(0L), object.getId());
		}

		// be sure that all the objects were saved
		int expectedSize = testObjectsNumber;
		int actualSize = objectList.size();
		assertEquals(expectedSize, actualSize);
	}



	@Test
	public void testSaveAll() throws Exception {

		mapService.saveAll(objectList);
		assertEquals(testObjectsNumber, mapService.findAll().size());
	}

	@Test
	public void testDelete() throws Exception {

		mapService.saveAll(objectList);
		int expectedSize = testObjectsNumber;
		
		for (TestObject object : objectList) {
			mapService.delete(object);
			expectedSize--;
			int actualSize = mapService.findAll().size();
			assertEquals(expectedSize, actualSize);
		}

		int objectListNewSize = mapService.findAll().size();
		assertEquals(0, objectListNewSize);
	}

	@Test
	public void testDeleteById() throws Exception {

		mapService.saveAll(objectList);
		int expectedSize = testObjectsNumber;
		
		for (TestObject object : objectList) {
			Long testObjectId = object.getId();
			mapService.deleteById(testObjectId);
			expectedSize--;
			int actualSize = mapService.findAll().size();
			assertEquals(expectedSize, actualSize);
		}

		int objectListNewSize = mapService.findAll().size();
		assertEquals(0, objectListNewSize);
	}

}
