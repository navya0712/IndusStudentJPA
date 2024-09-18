package com.indus.training.persist.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.indus.training.persist.dao.StudentDao;
import com.indus.training.persist.entity.Student;
import com.indus.training.persist.util.EntityManagerUtil;

/**
 * This class implements the StudentDao interface to handle operations for
 * Student entities, such as inserting, fetching, deleting, and updating using
 * JPA.
 */
public class StudentDaoImpl implements StudentDao {

//	private static final String PERSISTENCE_UNIT_NAME = "myPersistenceUnit";
//	private EntityManagerFactory emf;

	/**
	 * Initializes the EntityManagerFactory.
	 */
//	public StudentDaoImpl() {
//		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//	}

	/**
	 * Provides an EntityManager for database operations.
	 * 
	 * @return an EntityManager instance
	 */
//	private EntityManager getEntityManager() {
//		return emf.createEntityManager();
//	}

	/**
	 * Inserts a Student into the database.
	 * 
	 * @param stuObj the Student to be inserted
	 * @return true if the insertion was successful, false otherwise
	 * @throws NullPointerException if stuObj is null
	 */
	@Override
	public boolean insertStudent(Student stuObj) {
		if (stuObj == null) {
			throw new NullPointerException("Student object cannot be null");
		}

		boolean status = false;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			if (em.find(Student.class, stuObj.getStudentId()) == null) {
				em.persist(stuObj);
				em.getTransaction().commit();
				status = true;
			} else {
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			handleException(em, e);
		} finally {
			closeEntityManager(em);
		}

		return status;
	}

	/**
	 * Fetches a Student from the database by ID.
	 * 
	 * @param stuId the ID of the Student to be fetched
	 * @return the Student with the specified ID, or null if not found
	 */
	@Override
	public Student fetchStudent(int stuId) {
		Student stuObj = null;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			stuObj = em.find(Student.class, stuId);
		} finally {
			closeEntityManager(em);
		}

		return stuObj;
	}

	/**
	 * Deletes a Student from the database by ID.
	 * 
	 * @param stuId the ID of the Student to be deleted
	 * @return true if the deletion was successful, false otherwise
	 */
	@Override
	public boolean deleteStudent(int stuId) {
		boolean status = false;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			Student stuObj = em.find(Student.class, stuId);
			if (stuObj != null) {
				em.remove(stuObj);
				em.getTransaction().commit();
				status = true;
			} else {
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			handleException(em, e);
		} finally {
			closeEntityManager(em);
		}

		return status;
	}

	/**
	 * Updates the first name of a Student.
	 * 
	 * @param id           the ID of the Student to be updated
	 * @param updFirstName the new first name
	 * @return true if the update was successful, false otherwise
	 */
	@Override
	public boolean updateStudentFirstName(int id, String updFirstName) {
		boolean status = false;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			Student stuObj = em.find(Student.class, id);
			if (stuObj != null) {
				stuObj.setFirstName(updFirstName);
				em.getTransaction().commit();
				status = true;
			} else {
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			handleException(em, e);
		} finally {
			closeEntityManager(em);
		}

		return status;
	}

	/**
	 * Updates the last name of a Student.
	 * 
	 * @param id          the ID of the Student to be updated
	 * @param updLastName the new last name
	 * @return true if the update was successful, false otherwise
	 */
	@Override
	public boolean updateStudentLastName(int id, String updLastName) {
		boolean status = false;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			Student stuObj = em.find(Student.class, id);
			if (stuObj != null) {
				stuObj.setLastName(updLastName);
				em.getTransaction().commit();
				status = true;
			} else {
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			handleException(em, e);
		} finally {
			closeEntityManager(em);
		}

		return status;
	}

	/**
	 * Rolls back the transaction if an exception occurs.
	 * 
	 * @param em the EntityManager involved in the transaction
	 * @param e  the exception that occurred
	 */
	private void handleException(EntityManager em, Exception e) {
		if (em.getTransaction().isActive()) {
			em.getTransaction().rollback();
		}
	}

	/**
	 * Closes the EntityManager.
	 * 
	 * @param em the EntityManager to be closed
	 */
	private void closeEntityManager(EntityManager em) {
		if (em != null) {
			em.close();
		}
	}
}
