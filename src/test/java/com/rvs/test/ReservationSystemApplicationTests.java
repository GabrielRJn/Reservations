package com.rvs.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rvs.ReservationRepository.ReservationRepo;
import com.rvs.model.Reservation;
import com.rvs.service.ReservationService;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest("webEnvironment = WebEnvironment.RANDOM_PORT")
@AutoConfigureMockMvc

@Sql(scripts = {"classpath:reservations-schema.sql",
"classpath:reservations-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
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
		    	
		    	//given
		    final Reservation TEST_RESERVATION = new Reservation
																(0L,
																"Dean",
																"Dean@gmail.com",
															Date.valueOf("2022-08-26"),
																"table 5/Wed/5PM");  
		       
		    final Reservation TEST_SAVED_RESERVATION = new Reservation
											        				(1L,
																	"Dean",
																	"Dean@gmail.com",
																	Date.valueOf("2022-08-26"),
																	"table 5/Wed/5PM");

		     //Testing saves from RepositoryRepo
		    //when
		     Mockito.when(this.repo.save(TEST_RESERVATION)).thenReturn(TEST_SAVED_RESERVATION);

		     //then
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
			  Date.valueOf("2022-08-26"), "table 5/Wed/5PM");
			  
			  
			  
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
																		  Date.valueOf("2022-08-26"),
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
						  Date.valueOf("2022-08-26"),
						  "table 5/Wed/5PM");
				  
				
			    
	       
			       
			 
			  repo.save(TEST_SAVED_RESERVATION);
			 
			  
			  List<Reservation> listOfReservations = new ArrayList<Reservation>();
			  
			  service.getAllReservations().stream().forEach(reservation -> listOfReservations.add(reservation));
			  
			  
			  
			  assertNotNull(listOfReservations);
			  assertEquals(1, listOfReservations.size());
			  assertThat(listOfReservations.contains(TEST_SAVED_RESERVATION));
			  
			  System.out.println("Test for Find All Successful"); 
			  }
			  
			  @Test public void testFindByCustomer() {
				  Long reservationId = 2000L; 
				  final Reservation TEST_SAVED_RESERVATION = new Reservation(reservationId, "Dean",
						  "Dean@gmail.com",
						  Date.valueOf("2022-08-26"),
						  "table 5/Wed/5PM");
				  
				
			    
	       
			       
			  
			  
			  
			  
			  
			  List<Reservation> listOfReservations  = service.findCustomerByName("Dean")
					  .stream().findFirst().orElse(Fail.fail("Test reservation not found"));
			  
			  
			  assertNotNull(listOfReservations);
			  assertEquals(1, listOfReservations.size());
			 
			  
			  System.out.println("Test for Find By customer Successful"); 
			  }
			  
			  
			  
			
			  //---------- Integration Test----------//
			  
			  
			  //Integration Test 1
			  
			  @Test public void IntTestCreate() throws Exception { 
				  Long reservationId = 2000L; 
				  
				  final Reservation TEST_SAVED_RESERVATION = new Reservation(
																	  reservationId, "Dean",
																	  "Dean@gmail.com",
																	  Date.valueOf("2022-08-26"),
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
			  listOfReservations); System.out.println(listOfReservations.size());
			  }
			
			  
			  //Integration Test 3
			  @Test
			  public void IntTestAddNewReservation() throws Exception{
				  final Reservation TEST_SAVED_RESERVATION = new Reservation(
						  43L, "Dean",
						  "Dean@gmail.com",
						  Date.valueOf("2022-08-26"),
						  "table 5/Wed/5PM");
				  
				 this.mock.perform(put("/addNewReservations")
			  			   .contentType(MediaType.APPLICATION_JSON)
						  .content(this.obmapper.writeValueAsString(TEST_SAVED_RESERVATION)))
				  		  .andExpect(status().is2xxSuccessful());
				 
				
				  
			
}
			  
			  //Integration Test 4
			  
			  @Test public void getReservationById() throws Exception {
			final Reservation TEST_SAVED_RESERVATION = new Reservation(
						  999L, "Dean",
						  "Dean@gmail.com",
						  Date.valueOf("2022-08-26"),
						  "table 5/Wed/5PM");
			
		      service.saveOrUpdate(TEST_SAVED_RESERVATION);
		
		
				  
			 String resultString= this.mock.perform(request(HttpMethod.GET,"/reservation/ids{reservationID}",TEST_SAVED_RESERVATION.getReservationID())
								  .accept(MediaType.APPLICATION_JSON))
								  .andExpect(status()
								  .isOk())
								  .andReturn().getResponse().getContentAsString();
						  
											  		  
			  List<Reservation> listOfReservations = Arrays.asList(obmapper
						   .readValue(resultString,Reservation[].class));
			  assertEquals(1, listOfReservations.size());
			
			  }
}
			  
				 
				  
			  
			  
			  
			  
			  
			  
			  
			  
			 
			 
		  
		  
