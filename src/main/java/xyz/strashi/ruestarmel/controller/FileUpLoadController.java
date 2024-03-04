package xyz.strashi.ruestarmel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.strashi.ruestarmel.exception.StorageFileNotFoundException;
import xyz.strashi.ruestarmel.service.StorageService;

import java.io.IOException;
import java.util.stream.Collectors;



@Controller
public class FileUpLoadController {

    private final StorageService storageService;

    @Autowired
    public FileUpLoadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/admin")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileUpLoadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));


        return "admin";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/admin")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/admin";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/admin/pageenplus/{string}")
    public String lapageenplus(@PathVariable("string") String string, Model model){
        model.addAttribute(string, "Coucou");
        System.out.println("Page en plus");
        return "pageenplus";
    }
    @GetMapping("/admin/delete/{file}")
    public String deleteFile(@PathVariable("file") String file ) throws IOException {

        System.out.println(file);
        storageService.deleteByFile(file);
        return "redirect:/admin";
    }
}
