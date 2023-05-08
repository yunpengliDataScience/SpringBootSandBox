package org.dragon.yunpeng.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileController {

	@GetMapping("/listFiles")
	public String listFiles(Model model, @RequestParam(name = "sort", defaultValue = "name") String sort,
			@RequestParam(name = "dir", defaultValue = "asc") String dir) {
		List<File> files = Arrays.asList(new File("src/main/resources/static/files").listFiles());
		Comparator<File> comparator = null;

		if (sort.equals("name")) {
			comparator = Comparator.comparing(File::getName);
		} else if (sort.equals("creationDate")) {
			comparator = Comparator.comparing(File::lastModified);
		}

		if (comparator != null) {
			if (dir.equals("desc")) {
				comparator = comparator.reversed();
			}
			files.sort(comparator);
		}

		if (dir.equals("asc")) {
			dir = "desc";
		} else {
			dir = "asc";
		}

		model.addAttribute("files", files);
		model.addAttribute("sort", sort);
		model.addAttribute("dir", dir);
		return "listFiles";
	}
}
