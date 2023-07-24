package com.ruoyi.activiti.util;

import com.ruoyi.activiti.util.ConversionEnums.CamundaTag;
import com.ruoyi.activiti.util.ConversionEnums.NamespaceName;
import org.activiti.bpmn.converter.BaseBpmnXMLConverter;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.AbstractAttribute;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.List;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.xml.sax.InputSource;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;

public class ActivitiUtils {

    public static BpmnModel convertToBpmnModel(String bpmnXml) {
        BpmnModel bpmnModel = null;
        try {
            InputStream bpmnStream = new ByteArrayInputStream(bpmnXml.getBytes());// 获取bpmn2.0规范的xml
            XMLInputFactory xif = XMLInputFactory.newInstance();
            InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
            XMLStreamReader xtr = xif.createXMLStreamReader(in);
            // 然后转为bpmnModel
            bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bpmnModel;
    }

    /**
     * 文件对象转字符串
     *
     * @param document
     * @return
     */
    public static String convertDocumentToString(Document document) {
        StringWriter stringWriter = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint(); // You can use createCompactFormat() for a compact output
        XMLWriter writer = new XMLWriter(stringWriter, format);
        try {
            writer.write(document);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }

    /**
     * 备注 属性转换
     * 参数 [file, flag camunda->activiti=true  activiti->camunda=false]
     */
    public static String camundaToActiviti(String xml, Boolean flag) {
        //判断文件后缀名是否正确
        try {
            //转换为文档
            // 转换为Document
            Document doc = DocumentHelper.parseText(xml);
            //获取根节点
            Element rootElement = doc.getRootElement();
            //获取命名空间
            //flag camunda->activiti=true  activiti->camunda=false
            NamespaceHandler(rootElement, flag);
            //处理userTask
            Element process = rootElement.element("process");
            List<Element> userTask = process.elements("userTask");
            CamundaTag[] values = CamundaTag.values();
            if (!CollectionUtils.isEmpty(userTask)) {
                for (CamundaTag value : values) {
                    userTask(userTask, value, flag);
                }
                //处理监听
                userTaskListener(userTask, flag);
            }
            return convertDocumentToString(doc);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return xml;
    }

    /**
     * 备注 处理命名空间
     * 参数 [rootElement, flag]
     */
    public static void NamespaceHandler(Element rootElement, Boolean flag) {
        NamespaceName camundaNamespace = NamespaceName.camunda;
        NamespaceName activitiNamespace = NamespaceName.activiti;
        Namespace activiti = rootElement.getNamespaceForPrefix(flag ? camundaNamespace.getName() : activitiNamespace.getName());
        if (activiti != null) {
            //删除camunda命名空间
            rootElement.remove(activiti);
            //添加activiti命名空间
            Namespace namespace = new Namespace(flag ? activitiNamespace.getName() : camundaNamespace.getName(), flag ? activitiNamespace.getUrl() : camundaNamespace.getUrl());
            rootElement.add(namespace);
        }
    }

    /**
     * 备注 userTask 参数转换
     * 参数 [userTask 要处理的集合, camundaTag 要处理的参数类型, flag camunda->activiti=true  activiti->camunda=false]
     */
    public static void userTask(List<Element> userTask, CamundaTag camundaTag, Boolean flag) {
        for (Element element : userTask) {
            Attribute assignee = element.attribute(camundaTag.getAttribute());
            if (assignee != null) {
                element.remove(assignee);
                AbstractAttribute abstractAttribute = new AbstractAttribute() {
                    @Override
                    public QName getQName() {
                        QName qName = new QName(flag ? camundaTag.getActivitiName() : camundaTag.getCamundaName());
                        return qName;
                    }

                    @Override
                    public String getValue() {
                        return assignee.getValue();
                    }
                };
                element.add(abstractAttribute);
            }
        }
    }

    /**
     * 备注 userTask 参数转换
     * 参数 [userTask 要处理的集合, camundaTag 要处理的参数类型, flag camunda->activiti=true  activiti->camunda=false]
     */
    public static void userTaskListener(List<Element> userTask, Boolean flag) {
        CamundaTag listener = CamundaTag.taskListener;
        for (Element element : userTask) {
            Element extensionElements = element.element("extensionElements");
            if (extensionElements != null) {
                //是否有监听
                Element taskListener = extensionElements.element(listener.getAttribute());
                if (taskListener != null) {
                    //获取节点所有参数
                    List<Attribute> attributes = taskListener.attributes();
                    DefaultElement defaultElement = new DefaultElement(flag ? listener.getActivitiName() : listener.getCamundaName());
                    //将参数拷贝到新的节点
                    for (Attribute attribute : attributes) {
                        DefaultAttribute defaultAttribute = new DefaultAttribute(attribute.getName(), attribute.getValue());
                        defaultElement.add(defaultAttribute);
                    }
                    //删除原有节点
                    extensionElements.remove(taskListener);
                    //添加新构建的节点
                    extensionElements.add(defaultElement);
                }

            }
        }
    }
}
