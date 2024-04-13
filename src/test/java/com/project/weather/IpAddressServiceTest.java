package com.project.weather;

import com.project.weather.Service.IpAddressService;
import org.junit.jupiter.api.Test;

import jakarta.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IpAddressServiceTest {

    @Test
    void getClientIpAddress_ShouldReturnCorrectIpAddress() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        // Assume your actual implementation calls request.getHeader("X-Forwarded-For")
        // We stub the behavior for the purpose of the test
        when(request.getHeader("X-Forwarded-For")).thenReturn("127.0.0.1");

        // Your actual test code
        String ipAddress = IpAddressService.getClientIpAddress(request);

        assertEquals("127.0.0.1", ipAddress);

        // You can remove the unnecessary stubbings as follows:
        verify(request).getHeader("X-Forwarded-For");
        verifyNoMoreInteractions(request);
    }

    @Test
    void getClientIpAddress_WithNullHeaders_ShouldReturnRemoteAddr() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        // Your actual test code
        String ipAddress = IpAddressService.getClientIpAddress(request);

        assertEquals(request.getRemoteAddr(), ipAddress);
    }
}
