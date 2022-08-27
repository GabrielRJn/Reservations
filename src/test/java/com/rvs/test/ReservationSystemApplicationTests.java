package com.rvs.test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rvs.ReservationRepository.ReservationRepo;
import com.rvs.model.Reservation;
import com.rvs.service.ReservationService;


@AutoConfigureMockMvc
@SpringBootTest
//@Sql(scripts = { "classpath:reservations-data.sql",
//"classpath:reservations-schema.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class ReservationSystemApplicationTests {
	

	
		  	@Autowired
		    private ReservationService service;

		    @MockBean
		    private ReservationRepo repo;
		    
		    @Autowired
		    private MockMvc mock;
		    
		    
		   
		    @Autowired
			private ObjectMapper obmapper;
		    

		    //Unit Test 1 - SAVING A NEW RESERVATION IN THE REPOSITORY FROM THE SERVICE CLASS
		    @Test
		    void testCreate() {
		    	
		    //This is a made up person to perform tests against	
		    final Reservation TEST_RESERVATION = new Reservation
																(0L,
																"Dean",
																"Dean@gmail.com",
															Date.valueOf("2022-08-26T15:34:39.386"),
																"table 5/Wed/5PM");  
		       
		    final Reservation TEST_SAVED_RESERVATION = new Reservation
											        				(1L,
																	"Dean",
																	"Dean@gmail.com",
																	Date.valueOf("2022-08-26T15:34:39.386"),
																	"table 5/Wed/5PM");

		     //Testing saves from RepositoryRepo
		     Mockito.when(this.repo.save(TEST_RESERVATION)).thenReturn(TEST_SAVED_RESERVATION);

		     //Testing saves from ReservationsService
		     Assertions.assertThat(this.service.saveOrUpdate(TEST_RESERVATION)).isEqualTo(TEST_SAVED_RESERVATION);

		     //Check if test reservation entered the mock repository once
		     Mockito.verify(this.repo, Mockito.times(1)).save(TEST_RESERVATION);
		        
		     System.out.println("Unit test 1 successful ");
		        //
		    }


			
			  //Unit Test 2
			  
			  @Test 
			  void testGetById() {
			  
			  
			  Long reservationId = 2000L; 
			  final Reservation TEST_RESERVATION = new
			  Reservation (reservationId, "Dean", "Dean@gmail.com",
			  Date.valueOf("2022-08-26T15:34:39.386"), "table 5/Wed/5PM");
			  
			  
			  
			  Mockito.when(this.repo.findById(reservationId)).thenReturn(Optional.of(TEST_RESERVATION));
					  																	
			  Assertions.assertThat(
								  this.service.getReservationById(reservationId)
								  .equals(this.service.getReservationById(reservationId)));
			 
			  
			  Mockito.verify(this.repo, Mockito.times(2)).findById(reservationId);
			  
			  System.out.println("Get By ID tests are successful");
			  
			  }
	
			
			  //Unit Test 3
			  
			  @Test 
			  public void testDeleteReservationById() {
			  
			  
			  
			  Long reservationId = 2000L;   
			  final Reservation TEST_SAVED_RESERVATION = new Reservation(reservationId, "Dean",
																		  "Dean@gmail.com",
																		  Date.valueOf("2022-08-26T15:34:39.386"),
																		  "table 5/Wed/5PM");
			  
			  
			  service.delete(TEST_SAVED_RESERVATION.getReservationID());
	
			  Mockito.verify(repo, Mockito.times(1)).deleteById(TEST_SAVED_RESERVATION.getReservationID());
			  
			  System.out.println("Test for Delete By ID Successful");
			  
			  }
			  
			  //Unit Test 4
			  
			  @Test public void testFindAll() {
				  Long reservationId = 2000L; 
				  final Reservation TEST_SAVED_RESERVATION = new Reservation(reservationId, "Dean",
						  "Dean@gmail.com",
						  Date.valueOf("2022-08-26T15:34:39.386"),
						  "table 5/Wed/5PM");
				  
				
			      

			        
			        System.out.println("Test for Find All Successful");
  
			  
			  List<Reservation> listOfReservations = service.getAllReservations();
			  listOfReservations.add(TEST_SAVED_RESERVATION);
			  
			  
			  
			  assertNotNull(listOfReservations);
			  assertEquals(1, listOfReservations.size());
			  
			  System.out.println("Test for Find All Successful"); }
			  
			
			  //---------- Integration Test----------//
			  
			  
			  //Integration Test 1
			  
			  @Test public void IntTestCreate() throws Exception { 
				  Long reservationId = 2000L; 
				  
				  final Reservation TEST_SAVED_RESERVATION = new Reservation(
																	  reservationId, "Dean",
																	  "Dean@gmail.com",
																	  Date.valueOf("2022-08-26T15:34:39.386"),
																	  "table 5/Wed/5PM");
			  
			  this.mock.perform(post("/updateReservation")
												  			   .contentType(MediaType.APPLICATION_JSON)
															  .content(this.obmapper.writeValueAsString(TEST_SAVED_RESERVATION)))
													  		  .andExpect(status().isCreated()); 
			  }
			  
			  
			  
			  //Integration Test 2
			  
			  @Test public void IntTestReadAll() throws Exception {
				  
			  final String resultString = this.mock.perform(
											  		   request(HttpMethod.GET,"/allReservations")
													  .accept(MediaType.APPLICATION_JSON))
													  .andExpect(status()
													  .isOk()).andReturn().getResponse().getContentAsString();
			  
			  List<Reservation> listOfReservations = Arrays.asList(obmapper
					  									   .readValue(resultString,Reservation[].class));
			  
			  assertEquals(new ArrayList<>(Arrays.asList()),
			  listOfReservations); System.out.println(listOfReservations.size()); }
			 
		  
		  
}