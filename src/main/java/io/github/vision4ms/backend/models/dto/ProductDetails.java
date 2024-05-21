package io.github.vision4ms.backend.models.dto;

import java.util.Set;

public record ProductDetails(String name, String description, Set<String> depends) {
}
