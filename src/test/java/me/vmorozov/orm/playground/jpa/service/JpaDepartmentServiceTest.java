package me.vmorozov.orm.playground.jpa.service;

import me.vmorozov.orm.playground.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaDepartmentServiceTest extends IntegrationTest {

    @Autowired
    private JpaDepartmentService jpaDepartmentService;

    @Test
    public void shouldDemonstrateMagic() {
        jpaDepartmentService.demonstrateMagic();
    }

}