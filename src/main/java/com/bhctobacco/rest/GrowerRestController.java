package com.bhctobacco.rest;

//import com.bhctobacco.model.*;
import antlr.collections.List;
import com.bhctobacco.dto.PalletLoadRequest;
import com.bhctobacco.dto.ReworksRequest;
import com.bhctobacco.enums.Rebate;
import com.bhctobacco.model.Bale;
import com.bhctobacco.model.Debt;
import com.bhctobacco.dto.GrowerCalculationsRequest;
import com.bhctobacco.dto.TaxCalculationsRequest;
import com.bhctobacco.model.Pallet;
import com.bhctobacco.service.GrowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grower")
@Api(value = "grower", description = "Grower calculations")
@CrossOrigin
public class GrowerRestController {

    private final GrowerService growerService;

    @Autowired
    public GrowerRestController(GrowerService growerService) {
        this.growerService = growerService;
    }

    @PostMapping("/calculate/gross")
    @ApiOperation(value = "Calculate Gross Earnings for a List of Bales for a particular grower")
    Double calculateGross(@RequestBody GrowerCalculationsRequest request) {
        return growerService.calculateGross(request.getBaleList(), request.getGrowerNumber());
    }
    @PostMapping("/calculate/taxes")
    @ApiOperation(value = "Calculate net amount after taxes for  particular grower")
    Double calculateNetTaxes(@RequestBody TaxCalculationsRequest taxCalculationsRequest) {
        return growerService.calculateCombinedTaxes(taxCalculationsRequest.getBaleList(), taxCalculationsRequest.getGrowerNumber());
    }

    @PostMapping("/calculate/debt")
    @ApiOperation(value = "Calculate net amount after debt for  particular grower")
    Double calculateDebt(@RequestBody GrowerCalculationsRequest growerCalculationsRequest, Debt debt) {
        return growerService.calculateDebt(growerCalculationsRequest.getBaleList(),growerCalculationsRequest.getGrowerNumber(),debt);
    }

    @PostMapping("/calculate/rebate")
    @ApiOperation(value = "Calculate net amount after rebate for  particular grower")
    Double calculateRebate(@RequestBody GrowerCalculationsRequest growerCalculationsRequest, Rebate rebate) {
        return growerService.calculateRebate(growerCalculationsRequest.getBaleList(),growerCalculationsRequest.getGrowerNumber(),rebate );
    }

    @PostMapping("/calculate/process_sale")
    @ApiOperation(value = "Calculate net amount for  particular grower")
    Double calculateProcessSale(@RequestBody GrowerCalculationsRequest growerCalculationsRequest,Debt debt ,Rebate rebate) {
        return growerService.calculateProcessSale(growerCalculationsRequest.getBaleList(), growerCalculationsRequest.getGrowerNumber(), rebate, debt);
    }
    @PostMapping("/calculate/reworks")
    @ApiOperation(value = "Reworks")
    Bale reworks(@RequestBody ReworksRequest reworksRequest) throws Exception {
        return growerService.reworks(reworksRequest.getBaleList());
    }
    @PostMapping("/load_pallet")
    @ApiOperation(value = "Load pallet")
    Pallet pallet(@RequestBody PalletLoadRequest palletLoadRequest)  {
        return growerService.loadPallet(palletLoadRequest.getBales());
    }

}
