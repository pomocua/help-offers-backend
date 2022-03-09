package ua.pomoc.helpoffers.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CacheUser {
    private UUID id;
    private Long expiredAt;
    private FilterModel filter;
    private PaginationModel pagination;
}
