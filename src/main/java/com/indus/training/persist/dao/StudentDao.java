package com.indus.training.persist.dao;

import com.indus.training.persist.entity.Student;
import com.indus.training.persist.exceptions.StudentHibernateJPAException;

public interface StudentDao {

	/**
	 * Inserts a new Student object into the database.
	 * 
	 * @param stuObj The Student object to be inserted.
	 * @return true if the student was successfully inserted, false otherwise.
	 * @throws NullPointerException if the Student object is null.
	 */
	public boolean insertStudent(Student stuObj) throws StudentHibernateJPAException;

	/**
	 * Fetches a Student object from the database by its ID.
	 * 
	 * @param stuId The ID of the Student to be fetched.
	 * @return The Student object with the given ID, or null if no such student
	 *         exists.
	 */
	public Student fetchStudent(int stuId);

	/**
	 * Deletes a Student object from the database by its ID.
	 * 
	 * @param stuId The ID of the Student to be deleted.
	 * @return true if the student was successfully deleted, false otherwise.
	 */
	public boolean deleteStudent(int stuId);

	/**
	 * Updates the first name of an existing Student in the database.
	 * 
	 * @param id           The ID of the Student to be updated.
	 * @param updFirstName The new first name for the Student.
	 * @return true if the first name was successfully updated, false otherwise.
	 */
	public boolean updateStudentFirstName(int id, String updFirstName);

	/**
	 * Updates the last name of an existing Student in the database.
	 * 
	 * @param id          The ID of the Student to be updated.
	 * @param updLastName The new last name for the Student.
	 * @return true if the last name was successfully updated, false otherwise.
	 */
	public boolean updateStudentLastName(int id, String updLastName);

}
