package CourseProj2.service;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponsModelList {
    private List<ResponsModel> listModel;
    private int amount;
    private String mes;
}
