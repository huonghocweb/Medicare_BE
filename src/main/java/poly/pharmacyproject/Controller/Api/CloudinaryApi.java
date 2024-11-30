package poly.pharmacyproject.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poly.pharmacyproject.Service.CloudinaryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cloudinary")
public class CloudinaryApi {

    @Autowired
    private CloudinaryService cloudinaryService;

}
