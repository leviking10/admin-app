package sn.isi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import sn.isi.dto.AppRolesDto;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class AppRolesServiceTest {

    @Autowired
    private AppRolesService appRolesService;

    @Test
    void getAppRoles() {
        List<AppRolesDto> appRolesDtoList =
                appRolesService.getAppRoles();

        Assertions.assertEquals(1, appRolesDtoList.size());
    }

    @Test
    void getAppRole() {
        AppRolesDto appRolesDto = appRolesService.getAppRole(1);

        Assertions.assertNotNull(appRolesDto);
    }

    @Test
    void createAppRoles() {

        AppRolesDto appRolesDto = new AppRolesDto();
        appRolesDto.setNom("ROLE_USER");

        AppRolesDto appRolesDtoSave = appRolesService.createAppRoles(appRolesDto);

        Assertions.assertNotNull(appRolesDtoSave);
        //Assertions.assertEquals(appRoles.getNom(), appRolesSave.getNom());
    }

    @Test
    void updateAppRoles() {
        AppRolesDto appRolesDto = new AppRolesDto();
        appRolesDto.setNom("ROLE_TECH");

        AppRolesDto appRolesDtoSave = appRolesService.updateAppRoles(3, appRolesDto);

        Assertions.assertEquals("ROLE_TECH", appRolesDtoSave.getNom());

    }

    @Test
    void deleteAppRoles() {

        appRolesService.deleteAppRoles(3);
        Assertions.assertTrue(true);
    }

    @Test
    void operation() {
        int val1 = 1;
        int val2 = 2;
        int val3 = val1 + val2;
        Assertions.assertEquals(3, val3);
    }
}
