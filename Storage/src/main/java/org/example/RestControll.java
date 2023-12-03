package org.example;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/usefulObjects")
public class RestControll
{

    private final RequestService requestService;
    private final Readable readable;
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addNewUsefulObject(@RequestBody UsefulObject obj)
    {
        requestService.addUsefulObject(obj);
    }
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deletedUsefulObject(UsefulObject obj)
    {
        requestService.deletedUsefulObject(obj);
    }

    @RequestMapping(value = "/GetID", method = RequestMethod.GET)
    public UsefulObject getUsefulObjectByID(@RequestBody Long id)
    {
        return requestService.getUsefulObjectByID(id);
    }

    @RequestMapping(value = "/GetNames", method = RequestMethod.GET)
    public List<UsefulObject> getUsefulObjectByName(String name)
    {
        return requestService.getUsefulObjectsByName(name);
    }
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateUsefulObject(Long oldIDObject,
                                      UsefulObject newObj)
    {
       UsefulObject updateObj = requestService.getUsefulObjectByID(oldIDObject);

        if(!requestService.deletedUsefulObject(updateObj))
            return;

        updateObj.setName(newObj.getName());
        updateObj.setLink(newObj.getLink());
        updateObj.setDescription(newObj.getDescription());

        if(!requestService.addUsefulObject(updateObj))
            return;
    }

}
