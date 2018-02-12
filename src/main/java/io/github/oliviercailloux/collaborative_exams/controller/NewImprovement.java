package io.github.oliviercailloux.collaborative_exams.controller;

import io.github.oliviercailloux.collaborative_exams.Service.ImprovementService;
import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.Service.SameAbilityService;
import io.github.oliviercailloux.collaborative_exams.model.entity.Improvement;
import io.github.oliviercailloux.collaborative_exams.model.entity.SameAbility;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;


@Path("NewImprovement")
public class NewImprovement {

	@Inject
	private QuestionService questionService;

	@Inject
	private PersonService personService;

	@Inject
	private ImprovementService ImprovementService;

	@Inject
	private SameAbilityService SameAbilityService;


	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String newImprovement(MultivaluedMap<String, String> form) throws Exception {
		
		int idQuestion1  = Integer.valueOf(form.getFirst("idQuestion1"));

		int idQuestion2  = Integer.valueOf(form.getFirst("idQuestion2"));
		
		int idAuthor  = Integer.valueOf(form.getFirst("idAuthor"));


		if(questionService.findQuestion(idQuestion1)==null)
			return "ID Q1 : " + idQuestion1 + " n\'existe pas ";
		else
			if(questionService.findQuestion(idQuestion2)==null)
				return "ID Q2 : " + idQuestion2 + " n\'existe pas ";
		else
			if(personService.findPerson(idAuthor)==null)
				return "ID Author : " + idAuthor + " n\'existe pas ";

		SameAbility sameability = SameAbilityService.isSameAbility(personService.findPerson(idAuthor),questionService.findQuestion(idQuestion1), questionService.findQuestion(idQuestion2));
		if (sameability == null)
			return "La relation entre les deux questions nexiste pas ";

		Improvement s = new Improvement(questionService.findQuestion(idQuestion1),questionService.findQuestion(idQuestion2),personService.findPerson(idAuthor));
			ImprovementService.persist(s);

		return "id 1 : " + idQuestion1 + "id 2 :" + idQuestion2 + "id author : " + idAuthor;
		
	}

}
