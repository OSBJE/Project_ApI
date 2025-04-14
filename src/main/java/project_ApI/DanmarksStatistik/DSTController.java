package project_ApI.DanmarksStatistik;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project_ApI.DanmarksStatistik.model.DSTRequestDTO;

import java.util.Map;

@Controller
public class DSTController {


    @Autowired
    private DSTService dstService;

    @GetMapping("/test2")
    public void test(){
        dstService.prompDST();

        System.out.println("its promping");

        //TODO
        //this is missing an return statement.
    }

    @GetMapping("/seeoutputDST")
    @ResponseBody
    public DSTRequestDTO seeOutput(){
        return dstService.getDST_Data();
    }

}
