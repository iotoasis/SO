package com.pineone.icbms.so.compositevo.pr;

import com.pineone.icbms.so.compositevo.entity.CompositeVirtualObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value ="/compositevo")
public class CompositeVirtualObjectPresentation {
    //
    /**
     * CVO 검색(list)
     * CVO 검색(By Id)
     * CVO 업데이트
     * CVO 삭제
     * CVO 실행(Id,Operation)
     * CVO 실행(IID,domain,functionality,operation)
     */

    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<CompositeVirtualObject> findCompositeVirtualObjectList(){
        List<CompositeVirtualObject> compositeVirtualObjects = new ArrayList<>();
        return compositeVirtualObjects;

    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public CompositeVirtualObject findCompostieVirtualObject(@PathVariable String id){
        CompositeVirtualObject compositeVirtualObject = new CompositeVirtualObject();


        return compositeVirtualObject;
    }
}
