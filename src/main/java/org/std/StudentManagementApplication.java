package org.std;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.std.dao.EtudiantRepository;
import org.std.entities.Etudiant;

@SpringBootApplication
public class StudentManagementApplication {

	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx = SpringApplication.run(StudentManagementApplication.class, args);
		/*EtudiantRepository etudiantRepository = ctx.getBean(EtudiantRepository.class);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		etudiantRepository.save(new Etudiant("Ahmed", df.parse("1990-08-06"), "ahmed@gmail.com", "ahmed.jpg"));
		etudiantRepository.save(new Etudiant("Marwen", df.parse("1995-04-13"), "marwen@gmail.com", "marwen.jpg"));
		etudiantRepository.save(new Etudiant("Soukaina", df.parse("1966-03-10"), "soukaina@gmail.com", "soukaina.jpg"));
		etudiantRepository.save(new Etudiant("Waheb", df.parse("1963-09-23"), "waheb@gmail.com", "waheb.jpg"));
		
		Page<Etudiant> et = etudiantRepository.findAll(PageRequest.of(0, 2));
		et.forEach(e-> System.out.println(e.getNom()));
		Page<Etudiant> et1 = etudiantRepository.chercherEtudiants("s", PageRequest.of(0, 2));
		et1.forEach(e->System.out.println(e.getNom()));*/
	}

}
