package com.example.jbastidas.tenpo.service;

import com.example.jbastidas.tenpo.entity.CallHistoryEntity;
import com.example.jbastidas.tenpo.repository.CallHistroyRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@EnableCaching
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExternalDataServiceIntegrationTest {

    @MockitoBean
    private CallHistroyRepo callHistroyRepo;

    @Autowired
    private ExternalDataService externalDataService;

    @Test
    void testFindPercentBD_isCached() throws ClassNotFoundException {
        // Arrange
        CallHistoryEntity mockEntity = new CallHistoryEntity();
        mockEntity.setRespuesta("{\"porcentaje\":8,\"id\":\"88\"}}");
        when(callHistroyRepo.findFirstByCodigoOrderByFechaDesc(200))
                .thenReturn(Optional.of(mockEntity));

        // Act
        System.out.println(externalDataService.findPercentBD().getRespuesta()); // Primera llamada
        System.out.println(externalDataService.findPercentBD().getRespuesta()); // Segunda llamada (debe usar cache)

        CallHistoryEntity result1 = externalDataService.findPercentBD(); // 1ra llamada: consulta repo
        CallHistoryEntity result2 = externalDataService.findPercentBD(); // 2da llamada: cache

        // Assert
        assertEquals(result1, result2);
        verify(callHistroyRepo, times(1)).findFirstByCodigoOrderByFechaDesc(200);
    }
}
