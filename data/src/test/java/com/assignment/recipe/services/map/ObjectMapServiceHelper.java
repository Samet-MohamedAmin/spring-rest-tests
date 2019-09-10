package com.assignment.recipe.services.map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.assignment.recipe.models.BaseEntity;
import com.assignment.recipe.services.CrudService;


public class ObjectMapServiceHelper<T extends BaseEntity> {

	// generates generic sample list of objects
    public Set<T> generateObjectList(int size, Class<T> objectClass) throws Exception {

		var objectList = new HashSet<T>();
		for (int i = 1; i <= size; i++) {
			T object = objectClass.getConstructor().newInstance();
			object.setId(Long.valueOf(i));
			objectList.add(object);
        }
        
        return objectList;
	}

	public void testFindAll(MapService<T, Long> mapService,
			CrudService<T, Long> objectMapService,
			Set<T> objectList) throws Exception {
		
		int objectsNumber = objectList.size();

		when(mapService.findAll()).thenReturn(objectList);
		Set<T> theObjectList = objectMapService.findAll();

		assertEquals(objectsNumber, theObjectList.size());
		verify(mapService, times(1)).findAll();
	}

	public void testFindById(MapService<T, Long> mapService,
			CrudService<T, Long> objectMapService,
			Set<T> objectList) throws Exception {

		for (T object : objectList) {
			Long theId = object.getId();
			when(mapService.findById(theId)).thenReturn(object);
			T objectFound = objectMapService.findById(object.getId());

			verify(mapService, times(1)).findById(eq(theId));
			assertNotNull(objectFound);
			assertEquals(object, objectFound);
		}
	}

	public void testSave(MapService<T, Long> mapService,
			CrudService<T, Long> objectMapService,
			Set<T> objectList) throws Exception {

		for (T object : objectList) {
			when(mapService.save(object)).thenReturn(object);
			T savePotato = objectMapService.save(object);

			verify(mapService, times(1)).save(eq(object));
			assertEquals(object, savePotato);
		}
	}

	public void testSaveAll(MapService<T, Long> mapService,
			CrudService<T, Long> objectMapService,
			Set<T> objectList) throws Exception {
		
		when(mapService.saveAll(objectList)).thenReturn(objectList);

		var actualPotatoList = new ArrayList<T>();
		objectMapService.saveAll(objectList).forEach(actualPotatoList::add);
		int expectedSize = objectList.size();
		int actualSize = actualPotatoList.size();
		
		// TODO: use argument capture to verify the argument
		verify(mapService, times(1)).saveAll(eq(objectList));
		assertEquals(expectedSize, actualSize);
	}

	public void testDelete(MapService<T, Long> mapService,
			CrudService<T, Long> objectMapService,
			Set<T> objectList) throws Exception {

		T thePotato = objectList.iterator().next();

		objectMapService.delete(thePotato);

		verify(mapService, times(1)).delete(eq(thePotato));
	}

	public void testDeleteById(MapService<T, Long> mapService,
			CrudService<T, Long> objectMapService,
			Set<T> objectList) throws Exception {

		T thePotato = objectList.iterator().next();
		Long objectId = thePotato.getId();

		objectMapService.deleteById(objectId);

		verify(mapService, times(1)).deleteById(eq(objectId));
	}

}
