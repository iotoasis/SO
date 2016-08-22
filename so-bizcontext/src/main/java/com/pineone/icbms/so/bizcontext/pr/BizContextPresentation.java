package com.pineone.icbms.so.bizcontext.pr;

import com.pineone.icbms.so.bizcontext.entity.CurrentElectricBiz;
import com.pineone.icbms.so.bizcontext.entity.InefficientElectricBiz;
import com.pineone.icbms.so.bizcontext.logic.*;
import com.pineone.icbms.so.bizcontext.ref.Biz_Note;
import com.pineone.icbms.so.bizcontext.ref.DataValidation;
import com.pineone.icbms.so.bizcontext.ref.ResponseMessage;
import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.util.exception.DataLossException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 3..
 * NOTE: BizContext 에서 제공하는 기능 오픈
 */
@Controller
@RequestMapping(value = "/biz")
@ResponseStatus(value = HttpStatus.OK)
@ResponseBody
public class BizContextPresentation {

    @Autowired
    BizContextBasicLogic bizContextBasicLogic;

    @Autowired
    CurrentElectricBizLogic currentElectricBizLogic;

    @Autowired
    InefficientElectricBizLogic inefficientElectricBizLogic;

    @Autowired
    WasteElectricBizLogic wasteElectricBizLogic;

    @Autowired
    LackKeyboardBizLogic lackKeyboardBizLogic;

    @Autowired
    LackPCBizLogic lackPCBizLogic;

    @Autowired
    LackMouseBizLogic lackMouseBizLogic;

    @Autowired
    ResponseMessage responseMessage;

    @Autowired
    DataValidation dataValidation;

    //NOTE: BizContext 생성 요청 -> 생성할 수 있는 BizContext 종류 리턴
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> retrieveBizContextList(){
        //
        List<String> bizContextList = bizContextBasicLogic.retrieveBizContextList();
        return bizContextList;
    }

    //NOTE : 선택된 BizContext 종류 획득
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage registerBizContext(@RequestBody String bizContextName){
        //
//        DataValidation dataValidation = DataValidation.newDataValidation();
//        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        try {
            dataValidation.inspectBizContext(bizContextName);
        } catch (DataLossException e) {
            responseMessage.setExceptionMessage(e.getMessage());
            return responseMessage;
        }
        String resultMessage = bizContextBasicLogic.registerBizContext(bizContextName);
        responseMessage.setMessage(resultMessage);
        return responseMessage;
    }

    //NOTE: BIZ Context 조건 충족 여부 판단
    public boolean isBizContextHappen(String bizContextName, Domain domain){
        boolean checkPoint = true;

        if(bizContextName.equals(Biz_Note.CURRENT_ELECTRIC.toString())){
            // NOTE : 현재 전력 사용량에 관한 데이터를 수신받기 위한 프레젠테이션 필요시 추가
            // NOTE : ex) Parameter : "ASPECT: 전력" + name 등..
            // NOTE : 데이터 수신후 currentBiz 에 Set 해서 전달.
            // NOTE : currentBizLogic.newCurrentBizLogic().isHappenBizContext(currentBiz) 형태
            checkPoint = currentElectricBizLogic.isHappenBizContext(domain);
        }
        else if(bizContextName.equals(Biz_Note.INEFFICIENT_ELECTRIC.toString())){
            // NOTE : 전력비효율에 관한 데이터를 수신받기위한 프레젠테이션 필요시 추가
            // NOTE : ex) Parameter : "ASPECT: 전력" , "Time : 시간" , "증가량 : 수치%" + name 등..
            // NOTE : 데이터 수신후 InefficientBiz Entity 에 Set 해서 전달.
            // NOTE : InefficientBizLogic.newInefficientBizLogic().isHappenBizContext(inefficientBiz) 형태
            checkPoint = inefficientElectricBizLogic.isHappenBizContext(domain);
        }
        else if(bizContextName.equals(Biz_Note.WASTE_ELECTRIC.toString())){
            // NOTE : 전력낭비에 관한 데이터를 수신받기위한 프레젠테이션 필요시 추가
            // NOTE : ex) Parameter : "ASPECT: 전력" + name 등..
            // NOTE : 데이터 수신후 WasteBiz Entity 에 Set 해서 전달.
            // NOTE : WasteBizLogic.newWasteBizLogic().isHappenBizContext(wasteBiz) 형태
            checkPoint = wasteElectricBizLogic.isHappenBizContext(domain);
        }
        return checkPoint;
    }

    //NOTE: BIZ Context 결과 필요한 수량 판단
    public String neededQuantity(String bizContextName, Domain domain){
        // NOTE : 기자재부족에 관한 데이터를 수신받기위한 프레젠테이션 필요시 추가
        // NOTE : ex) Parameter : "ASPECT: 기자재이름(mouse)" + name 등..
        // NOTE : 데이터 수신후 LackBiz Entity 에 Set 해서 전달.
        // NOTE : LackBizLogic.newLackBizLogic().isHappenBizContext(lackBiz) 형태

        String message = null;
        int needKeyboardQuantity = 0;
        int needMouseQuantity = 0;
        int needPCQuantity = 0;

        if(bizContextName.equals(Biz_Note.LACK_KEYBOARD.toString())){
            needKeyboardQuantity = lackKeyboardBizLogic.retrieveNeedQuantity(domain);
            message = "KeyBoard = " + needKeyboardQuantity + " 필요";
        }
        if(bizContextName.equals(Biz_Note.LACK_MOUSE.toString())){
            needMouseQuantity = lackMouseBizLogic.retrieveNeedQuantity(domain);
            message = "Mouse = " + needMouseQuantity + " 필요";
        }
        if(bizContextName.equals(Biz_Note.LACK_PC.toString())){
            needPCQuantity = lackPCBizLogic.retrieveNeedQuantity(domain);
            message = "PC = " + needPCQuantity + " 필요";
        }
        return message;
    }
}
