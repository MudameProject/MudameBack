package com.Mud.MudameB.Utils.messages;

public class ErrorMessages {
  
  public static String idNotFound(String entity) {
    final String message = "No se a encontrado la entidad %s con el id digitado";
    return String.format(message, entity);
  }

}
