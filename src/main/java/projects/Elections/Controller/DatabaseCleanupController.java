package projects.Elections.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import projects.Elections.Service.DatabaseCleanupService;

@RestController
public class DatabaseCleanupController {
    @Autowired
    private DatabaseCleanupService databaseCleanupService;
    @DeleteMapping("/deleteAllRecords")
    public String deleteAllRecords() {
        databaseCleanupService.deleteAllRecords();
        return "All records deleted";
    }
}
