package fr.slghive.heartlink.exceptions.handlers;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {

  private LocalDateTime timestamp;
  private String message;
  private int status;
  private String path;
}
