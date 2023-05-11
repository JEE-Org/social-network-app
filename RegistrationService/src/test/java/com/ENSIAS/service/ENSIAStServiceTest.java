package com.ENSIAS.service;

import com.ENSIAS.model.AuthResponse;
import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.LoginRequest;
import com.ENSIAS.model.RegistrationRequest;
import com.ENSIAS.repository.EnsiastRepository;
import com.ENSIAS.repository.TokenRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.ENSIAS.enums.Role;
import com.ENSIAS.enums.State;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedMetric;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;


//@AllArgsConstructor
@ExtendWith(MockitoExtension.class)
class ENSIAStServiceTest {
    @Mock
    private EnsiastRepository ensiastRepository;
    @Mock
    private TokenRepository tokenRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private ENSIAStService ensiaStService ;

    @BeforeEach
    void setUp() {
        //ensiastRepository = mock(EnsiastRepository.class) ;
//        tokenRepository = mock(TokenRepository.class) ;
        //bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class) ;
//        jwtService = mock(JwtService.class) ;
//        authenticationManager = mock(AuthenticationManager.class) ;

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registerENSIAStShouldReturnNull() {

        // given
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("test@example.com");
        when(ensiaStService.findByEmail("test@example.com")).thenReturn(Optional.of(new ENSIASt()));

        // when
        ENSIASt result = ensiaStService.registerENSIASt(request);

        // then
        assertNull(result);
        verify(ensiastRepository, never()).saveAndFlush(any(ENSIASt.class));

    }

    @Test
    void registerENSIAStShouldSaveNewENSIASt(){

        RegistrationRequest request = new RegistrationRequest();

        request.setEmail("test@elmrabti.com");
        request.setFirstName("Hamza");
        request.setLastName("Elmrabti");
        request.setPassword("password");
        request.setPromo(2025);
        request.setField("Computer Science");
        when(ensiaStService.findByEmail("test@elmrabti.com")).thenReturn(Optional.empty());
        when(bCryptPasswordEncoder.encode("password")).thenReturn("encoded_password");

        // when
        ENSIASt result = ensiaStService.registerENSIASt(request);

        // then
        assertNotNull(result);
        assertEquals("Hamza", result.getFirstName());
        assertEquals("Elmrabti", result.getLastName());
        assertEquals("test@elmrabti.com", result.getEmail());
        assertEquals(2025, result.getPromo());
        assertEquals("Computer Science", result.getField());
        assertEquals("encoded_password", result.getPassword());
        assertEquals(State.INACTIF, result.getState());
        assertEquals(Role.USER, result.getRole());
        verify(ensiastRepository, times(1)).saveAndFlush(result);

    }



    @Test
    void checkRegistrationShouldReturnConflict() {
        // given
        ENSIASt existingENSIASt = null;

        // when
        ResponseEntity<String> response = ensiaStService.checkRegistration(existingENSIASt);

        // then
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("This email is already registered", response.getBody());
    }


    @Test
    void checkRegistrationShouldReturnOk() {
        // given
        ENSIASt nonExistingENSIASt = new ENSIASt();

        // when
        ResponseEntity<String> response = ensiaStService.checkRegistration(nonExistingENSIASt);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ENSIASt created", response.getBody());
    }



    @Test
    void loginShouldReturnAuthResponseWithAccessTokenWhenEmailAndPasswordAreCorrect() {
        // given
        String email = "test@example.com";
        String password = "password";
        ENSIASt ensiaSt = new ENSIASt();
        ensiaSt.setEmail(email);
        ensiaSt.setPassword(bCryptPasswordEncoder.encode(password));
        when(ensiastRepository.findByEmail(email)).thenReturn(Optional.of(ensiaSt));
        LoginRequest request = new LoginRequest();
        request.setEmail(email);
        request.setPassword(password);

        // when
        AuthResponse result = ensiaStService.login(request);

        // then
        assertNotNull(result);
        assertNotNull(result.getAccessToken());
        verify(ensiastRepository, times(1)).findByEmail(email);
    }


    @Test
    void loginShouldReturnAuthResponseWithAccessTokenWhenEmailAndPasswordNotCorrect() {
        // given
        String email = "test@elmrabti.com";
        String password = "password";
        String email2 = "hamza@elmrabti.com";
        ENSIASt ensiaSt = new ENSIASt();
        ENSIASt ensiaSt2 = new ENSIASt() ;
        ensiaSt.setEmail(email);
        ensiaSt.setPassword(bCryptPasswordEncoder.encode(password));
        ensiaSt2.setEmail(email2);
        ensiaSt2.setPassword(bCryptPasswordEncoder.encode("555"));
        when(ensiastRepository.findByEmail(email)).thenReturn(Optional.of(ensiaSt));
        LoginRequest request = new LoginRequest();
        request.setEmail(email);
        request.setPassword(password);

        // when
        AuthResponse result = ensiaStService.login(request);

        // then
        //assertNotNull(result);
        //assertNotNull(result.getAccessToken());
        //verify(ensiastRepository, times(1)).saveAndFlush(ensiaSt);
        verify(ensiastRepository, times(1)).findByEmail(email) ;
    }


    @Test
    void login() {
    }

    @Test
    void findAll() {
    }

    //@Test
    //void findByEmail() {
    //}

    @Test
    void findByLastName() {
    }

    @Test
    void findByPromo() {
    }

    @Test
    void findByPromoAndField() {
    }

    @Test
    void findByField() {


    }

    @Test
    void findActiveENSIAStsShouldReturnActiveENSIASts() {
        // given
        List<ENSIASt> activeENSIASts = new ArrayList<>();
        ENSIASt ensiaSt1 = new ENSIASt() ;
        ENSIASt ensiaSt2 = new ENSIASt() ;

        ensiaSt1.setFirstName("Hamza");
        ensiaSt1.setLastName("Elmrabti");
        ensiaSt1.setPromo(2024);
        ensiaSt1.setEmail("hamza@elmrabti.com");
        ensiaSt1.setPassword("password");
        ensiaSt1.setField("Computer Science");
        ensiaSt1.setState(State.ACTIF);

        ensiaSt2.setFirstName("Yassine");
        ensiaSt2.setLastName("Alami");
        ensiaSt2.setPromo(2025);
        ensiaSt2.setEmail("yassine@gmail.com");
        ensiaSt2.setPassword("password");
        ensiaSt2.setField("Computer Science");
        ensiaSt2.setState(State.ACTIF);

        activeENSIASts.add(ensiaSt1) ;
        activeENSIASts.add(ensiaSt2) ;

        when(ensiastRepository.findByState(State.ACTIF)).thenReturn(Optional.of(activeENSIASts));

        Optional<List<ENSIASt>> result = ensiaStService.findActiveENSIASts();

        // then
        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());
        assertEquals("hamza@elmrabti.com", result.get().get(0).getEmail());
        assertEquals("yassine@gmail.com", result.get().get(1).getEmail());
        verify(ensiastRepository, times(1)).findByState(State.ACTIF);
    }



    @Test
    void addRoleShouldUpdateRoleToAdmin() {
        ENSIASt ensiaSt = new ENSIASt();
        ensiaSt.setEmail("test@elmrabti.com");
        ensiaSt.setRole(Role.USER);

        when(ensiastRepository.findByEmail("test@elmrabti.com")).thenReturn(Optional.of(ensiaSt));

        ensiaStService.addRole("test@elmrabti.com");

        verify(ensiastRepository, times(1)).saveAndFlush(argThat(updatedENSIASt ->
                updatedENSIASt.getEmail().equals("test@elmrabti.com")
                        && updatedENSIASt.getRole() == Role.ADMIN));
    }


}