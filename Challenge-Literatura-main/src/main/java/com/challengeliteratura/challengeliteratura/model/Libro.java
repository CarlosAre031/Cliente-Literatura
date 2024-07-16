package com.challengeliteratura.challengeliteratura.model;

import java.util.List;

public record Libro(String title, List<String> languages, int downloadCount, List<Autor> authors) {
}


