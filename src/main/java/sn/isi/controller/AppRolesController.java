package sn.isi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.AppRolesDto;
import sn.isi.service.AppRolesService;

@Controller
@RequestMapping("/approles")
public class AppRolesController {

    private static final Logger logger = LoggerFactory.getLogger(AppRolesController.class);
    private final AppRolesService appRolesService;

    public AppRolesController(AppRolesService appRolesService) {
        this.appRolesService = appRolesService;
    }

    @GetMapping("/")
    public String listAppRoles(Model model) {
        logger.debug("Affichage de la liste des rôles");
        model.addAttribute("roles", appRolesService.getAppRoles());
        return "approles/list-approles"; // Mise à jour du chemin de retour
    }

    @GetMapping("/new")
    public String showNewRoleForm(Model model) {
        logger.debug("Affichage du formulaire pour un nouveau rôle");
        AppRolesDto newRole = new AppRolesDto();
        model.addAttribute("role", newRole);
        return "approles/add-approles"; // Mise à jour du chemin de retour
    }

    @PostMapping("/save")
    public String saveRole(@ModelAttribute("role") AppRolesDto roleDto) {
        logger.debug("Enregistrement d'un nouveau rôle: {}", roleDto);
        appRolesService.createAppRoles(roleDto);
        return "redirect:/approles/";
    }

    @GetMapping("/edit/{id}")
    public String showEditRoleForm(@PathVariable int id, Model model) {
        logger.debug("Affichage du formulaire de modification pour le rôle ID: {}", id);
        AppRolesDto roleDto = appRolesService.getAppRole(id);
        model.addAttribute("role", roleDto);
        return "approles/update-approles"; // Mise à jour du chemin de retour
    }

    @PostMapping("/update/{id}")
    public String updateRole(@PathVariable int id, @ModelAttribute("role") AppRolesDto roleDto) {
        logger.debug("Mise à jour du rôle ID: {}", id);
        appRolesService.updateAppRoles(id, roleDto);
        return "redirect:/approles/";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable int id) {
        logger.debug("Suppression du rôle ID: {}", id);
        appRolesService.deleteAppRoles(id);
        return "redirect:/approles/";
    }
}
