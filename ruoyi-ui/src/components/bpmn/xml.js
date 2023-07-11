const xml = `
<?xml version="1.0" encoding="UTF-8"?>
<bpmn:definitions 
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xmlns:bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL" 
    xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" 
    xmlns:dc="http://www.omg.org/spec/DD/20100524/DC" 
    xmlns:di="http://www.omg.org/spec/DD/20100524/DI" 
    id="Definitions_0001" 
    targetNamespace="http://bpmn.io/schema/bpmn">
    <bpmn:process id="Process_1688657451823" name="业务流程_1688657451823" isExecutable="true">
        <bpmn:startEvent id="Event_1a33yyd">
            <bpmn:outgoing>Flow_1jdvoez</bpmn:outgoing>
        </bpmn:startEvent>
            <bpmn:userTask id="Activity_1d1cx92">
                <bpmn:incoming>Flow_1jdvoez</bpmn:incoming>
                <bpmn:outgoing>Flow_0tqgth8</bpmn:outgoing>
            </bpmn:userTask>
            <bpmn:sequenceFlow id="Flow_1jdvoez" sourceRef="Event_1a33yyd" targetRef="Activity_1d1cx92" />
            <bpmn:endEvent id="Event_073ypuq">
                <bpmn:incoming>Flow_0tqgth8</bpmn:incoming>
            </bpmn:endEvent>
            <bpmn:sequenceFlow id="Flow_0tqgth8" sourceRef="Activity_1d1cx92" targetRef="Event_073ypuq" />
    </bpmn:process>
    <bpmndi:BPMNDiagram id="BPMNDiagram_1">
        <bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="Process_1688657451823">
            <bpmndi:BPMNShape id="Event_1a33yyd_di" bpmnElement="Event_1a33yyd">
                <dc:Bounds x="42" y="152" width="36" height="36" />
            </bpmndi:BPMNShape>
            <bpmndi:BPMNShape id="Activity_1d1cx92_di" bpmnElement="Activity_1d1cx92">
                <dc:Bounds x="178" y="110" width="120" height="120" />
            </bpmndi:BPMNShape>
            <bpmndi:BPMNShape id="Event_073ypuq_di" bpmnElement="Event_073ypuq">
                <dc:Bounds x="398" y="152" width="36" height="36" />
            </bpmndi:BPMNShape>
            <bpmndi:BPMNEdge id="Flow_1jdvoez_di" bpmnElement="Flow_1jdvoez">
                <di:waypoint x="78" y="170" />
                <di:waypoint x="178" y="170" />
            </bpmndi:BPMNEdge>
            <bpmndi:BPMNEdge id="Flow_0tqgth8_di" bpmnElement="Flow_0tqgth8">
                <di:waypoint x="298" y="170" />
                <di:waypoint x="398" y="170" />
            </bpmndi:BPMNEdge>
        </bpmndi:BPMNPlane>
    </bpmndi:BPMNDiagram>
</bpmn:definitions>`
export default xml
