package io.github.oliviercailloux.collaborative_exams.controller;

import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.PersonTag;
import io.github.oliviercailloux.collaborative_exams.Service.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * JAX-RS Servlet that allows
 */
@Path("PersonTag")
public class GetPersonalTag {

    @Inject
    private PersonTagService persontagservice;

    /**
     * @return
     * @throws Exception
     */
    @Path("/all")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getPersonTag() throws Exception {
        List<PersonTag> tags = persontagservice.getAll();
        String result = "";
        for (PersonTag p : tags)
            result += QuestionText.ObjectToJson(PersonTag.class, p);
        return result;
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @Path("/{id}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getPersonTag(@PathParam("id") int id) throws Exception {
        PersonTag tags = persontagservice.findPersonTag(id);
        return QuestionText.ObjectToJson(PersonTag.class, tags);
    }


}
