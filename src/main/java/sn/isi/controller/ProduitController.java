package sn.isi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.ProduitDto;
import sn.isi.service.ProduitService;

@Controller
@RequestMapping("/produit")
public class ProduitController {
    private static final Logger logger = LoggerFactory.getLogger(ProduitController.class);
    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/")
    public String listProduits(Model model) {
        logger.debug("Affichage de la liste des produits");
        model.addAttribute("produits", produitService.getAllProduits());
        return "produit/list-produit";
    }

    @GetMapping("/new")
    public String showNewProduitForm(Model model) {
        logger.debug("Affichage du formulaire pour un nouveau produit");
        ProduitDto newProduit = new ProduitDto();
        model.addAttribute("produit", newProduit);
        return "produit/add-produit";
    }

    @PostMapping("/save")
    public String saveProduit(@ModelAttribute("produit") ProduitDto produitDto) {
        logger.debug("Enregistrement d'un nouveau produit: {}", produitDto);
        produitService.createProduit(produitDto);
        return "redirect:/produit/";
    }

    @GetMapping("/edit/{id}")
    public String showEditProduitForm(@PathVariable int id, Model model) {
        logger.debug("Affichage du formulaire de modification pour le produit ID: {}", id);
        ProduitDto produitDto = produitService.getProduit(id);
        model.addAttribute("produit", produitDto);
        return "produit/update-produit";
    }

    @PostMapping("/update/{id}")
    public String updateProduit(@PathVariable int id, @ModelAttribute("produit") ProduitDto produitDto) {
        logger.debug("Mise à jour du produit ID: {}", id);
        produitService.updateProduit(id, produitDto);
        return "redirect:/produit/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduit(@PathVariable int id) {
        logger.debug("Suppression du produit ID: {}", id);
        produitService.deleteProduit(id);
        return "redirect:/produit/";
    }
}
