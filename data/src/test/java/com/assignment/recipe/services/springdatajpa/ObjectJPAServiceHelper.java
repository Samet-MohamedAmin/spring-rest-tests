package com.assignment.recipe.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.assignment.recipe.models.BaseEntity;
import com.assignment.recipe.services.CrudService;

import org.springframework.data.repository.CrudRepository;

public class ObjectJPAServiceHelper<T extends BaseEntity> {

    // generates generic sample list of objects
    public Set<T> generateObjectList(int size, Class<T> objectClass) throws Exception {

		var objectList = new HashSet<T>();
		for (int i = 1; i <= size; i++) {
            // TODO: answer this https://stackoverflow.com/questions/2434041
			T object = objectClass.getConstructor().newInstance();
			object.setId(Long.valueOf(i));
			objectList.add(object);
        }
        
        return objectList;
	}

    public void testFindAll(CrudRepository<T, Long> objectRepository,
            CrudService<T, Long> objectService,
            Set<T> objectList) throws Exception {

        when(objectRepository.findAll()).thenReturn(List.copyOf(objectList));
        int actualSize = objectService.findAll().size();
        int expectedSize = objectList.size();

        assertEquals(expectedSize, actualSize);
        verify(objectRepository, times(1)).findAll();
    }

    public void testFindById(CrudRepository<T, Long> objectRepository,
            CrudService<T, Long> objectService,
            Set<T> objectList) throws Exception {
        
		for (T object : objectList) {
			Long theId = object.getId();
			when(objectRepository.findById(theId)).thenReturn(Optional.of(object));
			T objectFound = objectService.findById(object.getId());

			verify(objectRepository, times(1)).findById(eq(theId));
			assertNotNull(objectFound);
			assertEquals(object, objectFound);
		}
    }

    public void testSave(CrudRepository<T, Long> objectRepository,
            CrudService<T, Long> objectService,
            Set<T> objectList) throws Exception {

        for (T object : objectList) {
			when(objectRepository.save(object)).thenReturn(object);
			T savePotato = objectService.save(object);

			verify(objectRepository, times(1)).save(eq(object));
			assertEquals(object, savePotato);
		}
    }

    public void testSaveAll(CrudRepository<T, Long> objectRepository,
            CrudService<T, Long> objectService,
            Set<T> objectList) throws Exception {

		when(objectRepository.saveAll(objectList)).thenReturn(List.copyOf(objectList));

        var actualObjectList = objectService.saveAll(objectList);
		int expectedSize = objectList.size();
		int actualSize = actualObjectList.size();
        
        // TODO: use argument capture to verify the argument
		verify(objectRepository, times(1)).saveAll(eq(objectList));
		assertEquals(expectedSize, actualSize);
    }

    public void testDelete(CrudRepository<T, Long> objectRepository,
            CrudService<T, Long> objectService,
            Set<T> objectList) throws Exception {
        
        T thePotato = objectList.iterator().next();

		objectService.delete(thePotato);

		verify(objectRepository, times(1)).delete(eq(thePotato));
    }

    public void testDeleteById(CrudRepository<T, Long> objectRepository,
            CrudService<T, Long> objectService,
            Set<T> objectList) throws Exception {

        T thePotato = objectList.iterator().next();
		Long objectId = thePotato.getId();

		objectService.deleteById(objectId);

		verify(objectRepository, times(1)).deleteById(eq(objectId));
    }
}
