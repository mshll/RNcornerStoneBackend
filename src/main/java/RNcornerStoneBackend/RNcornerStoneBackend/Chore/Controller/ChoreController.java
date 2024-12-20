package RNcornerStoneBackend.RNcornerStoneBackend.Chore.Controller;

import RNcornerStoneBackend.RNcornerStoneBackend.Chore.Entity.ChoreEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.Chore.Entity.Status;
import RNcornerStoneBackend.RNcornerStoneBackend.Chore.Service.ChoreService;
import RNcornerStoneBackend.RNcornerStoneBackend.Chore.bo.ChoreRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.Chore.bo.ChoreResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/chores")
public class ChoreController {
    private final ChoreService choreService;

    public ChoreController(ChoreService choreService) {
        this.choreService = choreService;
    }

//    @PostMapping("/{parentId}/{childId}")
//    public ResponseEntity<ChoreResponse> createChore(
//            @PathVariable Long parentId,
//            @PathVariable Long childId,
//            @RequestBody ChoreResponse choreResponse) {
//        try {
//            ChoreResponse createdChore = choreService.createChore(parentId, childId, choreResponse);
//            return ResponseEntity.ok(createdChore);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }

    @PostMapping("/{childId}")
    public ResponseEntity<ChoreResponse> createChore(
            @PathVariable Long childId,
            @RequestBody ChoreResponse choreResponse) {
        try {
            // Call the service to create the chore for the authenticated parent and the specified child
            ChoreResponse createdChore = choreService.createChore(childId, choreResponse);
            return ResponseEntity.ok(createdChore);
        } catch (RuntimeException e) {
            // Return a bad request response in case of any exception
            return ResponseEntity.badRequest().body(null);
        }
    }

//    @PutMapping("/{choreId}/status")
//    public ResponseEntity<ChoreResponse> updateChoreStatus(
//            @PathVariable Long choreId,
//            @RequestParam Long parentId,
//            @RequestBody ChoreResponse choreResponse) {
//        try {
//            ChoreResponse updatedChore = choreService.updateChoreStatus(choreId, parentId, choreResponse.getStatus());
//            return ResponseEntity.ok(updatedChore);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }


    @PutMapping("/status/{choreId}")
    public ResponseEntity<ChoreResponse> updateChoreStatus(
            @PathVariable Long choreId,
            @RequestBody ChoreResponse choreResponse) {
        try {
            // Call the service to update the chore status for the authenticated parent
            ChoreResponse updatedChore = choreService.updateChoreStatus(choreId, choreResponse.getStatus());
            return ResponseEntity.ok(updatedChore);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }



}