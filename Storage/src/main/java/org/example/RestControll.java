package org.example;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class RestControll
{

    private final RequestService requestService;
    private final Readable readable;
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public UsefulObject addNewUsefulObject(@RequestBody UsefulObject obj)
    {
        requestService.addUsefulObject(obj);
        return obj;
    }
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public void deletedUsefulObject(@RequestBody UsefulObject obj)
    {
        requestService.deletedUsefulObject(obj);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public UsefulObject getUsefulObjectByID(@PathVariable Long id)
    {
        return requestService.getUsefulObjectByID(id);
    }

    @RequestMapping(value = "Names/{name}", method = RequestMethod.GET)
    public List<UsefulObject> getUsefulObjectByName(@PathVariable String name)
    {
        return requestService.getUsefulObjectsByName(name);
    }
    @RequestMapping(value = "update/{oldIDObject}", method = RequestMethod.PUT)
    public void updateUsefulObject(@PathVariable Long oldIDObject,
                                   @RequestBody UsefulObject newObj)
    {
        UsefulObject updateObj = requestService.getUsefulObjectByID(oldIDObject);
        requestService.deletedUsefulObject(updateObj);

        updateObj.setName(newObj.getName());
        updateObj.setLink(newObj.getLink());
        updateObj.setDescription(newObj.getDescription());

        requestService.addUsefulObject(updateObj);
    }

}
