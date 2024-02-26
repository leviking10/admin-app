package sn.isi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.AppUserDto;
import sn.isi.service.AppRolesService;
import sn.isi.service.AppUserService;

@Controller
@RequestMapping("/appuser")
public class AppUserController {
    private static final Logger logger = LoggerFactory.getLogger(AppUserController.class);
    private final AppUserService appUserService;
    private  final AppRolesService roleService;

    public AppUserController(AppUserService appUserService, AppRolesService roleService) {
        this.appUserService = appUserService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String listAppUsers(Model model) {
        logger.debug("Affichage de la liste des utilisateurs");
        model.addAttribute("users", appUserService.getAllUsers());
        return "appuser/list-appuser";
    }

    @GetMapping("/new")
    public String showNewUserForm(Model model) {
        AppUserDto newUser = new AppUserDto();
        model.addAttribute("user", newUser);
        model.addAttribute("roles", roleService.getAppRoles());
        return "appuser/add-appuser";
    }
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") AppUserDto userDto) {
        appUserService.createUser(userDto);
        return "redirect:/appuser/";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable int id, Model model) {
        AppUserDto userDto = appUserService.getUser(id);
        model.addAttribute("user", userDto);
        model.addAttribute("roles", roleService.getAppRoles());
        return "appuser/update-appuser";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute("user") AppUserDto userDto) {
        appUserService.updateUser(id, userDto);
        return "redirect:/appuser/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        appUserService.deleteUser(id);
        return "redirect:/appuser/";
    }
}
