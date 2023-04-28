// package com.ENSIAS;
// import com.ENSIAS.model.RegistrationRequest;
// import com.ENSIAS.service.*;
// import com.ENSIAS.controller.*;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import static org.mockito.Mockito.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// @SpringBootTest(classes=RegistrationServiceApplication.class)
// public class RegistrationTest {
//     private MockMvc mockMvc;

//     @Mock
//     private ENSIAStService ensiaStService;

//     @InjectMocks
//     private ENSIAStController controller;

//     @BeforeEach
//     public void setUp() {
//         mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//     }

//     @Test
//     public void testRegisterENSIASt() throws Exception {
//         RegistrationRequest request = new RegistrationRequest();
//         request.setFirstName("John");
//         request.setLastName("Doe");
//         request.setEmail("john.doe@example.com");
//         request.setPromo(2022);
//         request.setField("Computer Science");
//         request.setPassword("password");

//         doAnswer(invocation -> {
//             // your mock service logic here
//             return null;
//         }).when(ensiaStService).registerENSIASt(request);

//         mockMvc.perform(MockMvcRequestBuilders.post("/signup")
//             .content(asJsonString(request))
//             .contentType(MediaType.APPLICATION_JSON)
//             .accept(MediaType.APPLICATION_JSON))
//             .andExpect(MockMvcResultMatchers.status().isOk());

//         verify(ensiaStService, times(1)).registerENSIASt(request);
//     }

//     // A utility method to convert a Java object into JSON string
//     private static String asJsonString(final Object obj) {
//         try {
//             return new ObjectMapper().writeValueAsString(obj);
//         } catch (Exception e) {
//             throw new RuntimeException(e);
//         }
//     }
    
// }
