package com.code.module.project.service;

import com.code.module.base.bo.SaveFilePostionBO;
import com.code.module.base.bo.TableBO;
import com.code.module.base.bo.java.CreateJavaFileBO;
import com.code.module.combine.utils.CombineJavaUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JavaProjectService {

    public List<CreateJavaFileBO> combineCreateJavaFileBO(List<TableBO> tableBOS, SaveFilePostionBO saveFilePostionBO) throws Exception {
        List<CreateJavaFileBO> createJavaFileBOS = CombineJavaUtils.convertJavas(tableBOS);
        return createJavaFileBOS;
    }
}
