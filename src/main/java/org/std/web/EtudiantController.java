package org.std.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.std.dao.EtudiantRepository;
import org.std.entities.Etudiant;

@Controller
@RequestMapping(value = "/Etudiant")
public class EtudiantController {

	@Autowired	
    private EtudiantRepository etudiantRepository;
	
	@Value("${dir.images}")
	private String imageDir;
	
	@RequestMapping(value = "/Index")
	public String Index(Model model,@RequestParam(name="page",defaultValue = "0")int p,
			@RequestParam(name="motCle",defaultValue = "")String mc) {
		Page<Etudiant> etudiants = etudiantRepository.chercherEtudiants(mc,PageRequest.of(p, 5));
		int pagesCount = etudiants.getTotalPages();
		int[] pages = new int[pagesCount];
		for (int i = 0; i < pagesCount; i++) pages[i]=i;
		model.addAttribute("totalPage",pagesCount);
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("pageEtudiants", etudiants);
		model.addAttribute("motCle",mc);
		return "etudiants";
	}
	
	@RequestMapping(value="/Form",method = RequestMethod.GET)
	public String FormaEtudiant(Model model) {
		model.addAttribute("etudiant", new Etudiant());
		return "FormaEtudiant";
	}
	
	@RequestMapping(value="/SaveEtudiant",method = RequestMethod.POST)
	public String Save(@Valid Etudiant etd,BindingResult bindingResult,@RequestParam(name="picture") MultipartFile file) throws IllegalStateException, IOException {
		if (bindingResult.hasErrors()) {
			return "FormaEtudiant";	
		}
		if(!file.isEmpty()) {etd.setPhoto(file.getOriginalFilename());}
		etudiantRepository.save(etd);
		if (!file.isEmpty()) {
			//file.transferTo(new File(System.getProperty("user.home")+"/uploads/"+file.getOriginalFilename()));
			file.transferTo(new File(imageDir+etd.getId()));
		}
		return "redirect:Index";
	}
	
	@RequestMapping(value="/getPhoto",produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
	@ResponseBody
	public byte[] getPhoto(Long id) throws Exception {
		File f = new File(imageDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/delete")
	public String delete(Long id) {
		etudiantRepository.deleteById(id);
		return "redirect:Index";
	}
	
	@RequestMapping(value="/edit")
	public String edit(Long id,Model model) {
		Etudiant etd = etudiantRepository.getById(id);
		model.addAttribute("etudiant", etd);
		return "EditEtudiant";
	}
	
	@RequestMapping(value="/UpdateEtudiant",method = RequestMethod.POST)
	public String update(@Valid Etudiant etd,BindingResult bindingResult,@RequestParam(name="picture") MultipartFile file) throws IllegalStateException, IOException {
		if (bindingResult.hasErrors()) {
			return "EditEtudiant";	
		}
		if(!file.isEmpty()) {etd.setPhoto(file.getOriginalFilename());}
		etudiantRepository.save(etd);
		if (!file.isEmpty()) {
			//file.transferTo(new File(System.getProperty("user.home")+"/uploads/"+file.getOriginalFilename()));
			file.transferTo(new File(imageDir+etd.getId()));
		}
		return "redirect:Index";
	}
}
