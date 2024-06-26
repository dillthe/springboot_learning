package com.github.supercoding.web.controller;

import com.github.supercoding.service.ElectronicStoreItemService;
import com.github.supercoding.web.dto.BuyOrder;
import com.github.supercoding.web.dto.Item;
import com.github.supercoding.web.dto.ItemBody;
import com.github.supercoding.web.dto.airline.StoreInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ElectronicStoreController {

  //  private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ElectronicStoreItemService electronicStoreItemService;

    @Operation(summary = "모든 Item을 검색")
    @GetMapping("/items")
    public List<Item> findAllItem(){
   //     log.info("GET /items 요청이 들어왔습니다.");
       List<Item> items = electronicStoreItemService.findAllItem();
     //   log.info("GET /items 응답: " + items);
        return items;
    }

    @Operation(summary = "Item 등록")
    @PostMapping("/items")
    public String registerItem(@RequestBody ItemBody itemBody){
        Integer itemId = electronicStoreItemService.savaItem(itemBody);
        return "ID: " +  itemId;
    }


    @Operation(summary = "Item id로 검색(쿼리문)")
    @GetMapping("/items-query")
    public Item findItemByQueryId(
            @ApiParam(name="id", value="item ID", example = "1")
            @RequestParam("id") String id){
        return electronicStoreItemService.findItemById(id);
    }
    @Operation(summary = "Item ids(여러 아이디)로 검색(쿼리문)")
    @GetMapping("/items-queries")
    public List<Item> findItemByQueryIds(
            @ApiParam(name="ids", value="item IDs", example = "[1,2,3]")
            @RequestParam("id") List<String> ids){
        log.info("/items-queries 요청 ids:" + ids);
        List<Item>items = electronicStoreItemService.findItemsByIds(ids);
        log.info("/items-queries응답: " + items);
        return items;

    }

    @Operation(summary = "Item ids 수정")
    @PutMapping("/items/{id}")
    public Item updateItem(@PathVariable("id") String id, @RequestBody ItemBody itemBody){
        return electronicStoreItemService.updateItem(id, itemBody);
    }

    @Operation(summary = "Item id로 삭제")
    @DeleteMapping("/items/{id}")
    public String deleteItemByPathId(
            @ApiParam(name="id", value="item ID", example = "1")
            @PathVariable("id") String id){
        electronicStoreItemService.deleteItem(id);
        return "Object with id =" + id + "has been deleted";
    }



    @Operation(summary = "Item 구매")
    @PostMapping("/items/buy")
    public String buyItem(
            @RequestBody BuyOrder buyOrder){
        Integer orderItemNums = electronicStoreItemService.buyItems(buyOrder);
        return "요청하신 Item 중 " + orderItemNums + "개를 구매 하였습니다.";
    }

    @Operation(summary = "Item types로 검색(쿼리문)")
    @GetMapping("/items-types")
    public List<Item> findItemByTypes(
             @RequestParam("type") List<String> types){
        log.info("/items-types 요청 types:" + types);
        List<Item>items = electronicStoreItemService.findItemsByTypes(types);
        log.info("/items-types응답: " + items);
        return items;
    }

    @Operation(summary = "Item prices로 검색(쿼리문)")
    @GetMapping("/items-prices")
    public List<Item> findItemByPrices(
            @RequestParam("max") Integer maxValue){
        return electronicStoreItemService.findItemsOrderByPrices(maxValue);
    }

    @Operation(summary = "pagination 지원")
    @GetMapping("/items-page")
    public Page<Item> findItemsPagination(Pageable pageable){
        return electronicStoreItemService.findAllWithPageable(pageable);
    }

    @Operation(summary = "pagination 지원2")
    @GetMapping("/items-types-page")
    public Page<Item> findItemsPagination(@RequestParam("type") List<String> types, Pageable pageable){
        return electronicStoreItemService.findAllWithPageable(types, pageable);
    }

    @Operation(summary ="전체 stores 정보 검색")
    @GetMapping("/stores")
    public List<StoreInfo> findAllStoreInfo(){
        return electronicStoreItemService.findAllStoreInfo();
    }
}