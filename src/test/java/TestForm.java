import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

public class TestForm {
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule("activiti.cfg.xml");

	@Test
	@Deployment(resources = { "diagrams/MyProcess.bpmn" })
	public void TestFormStartEvent() {
		FormService formService = activitiRule.getFormService();
		String processDefId = activitiRule.getRepositoryService()
				.createProcessDefinitionQuery().singleResult().getId();

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("name", "timmy");
		properties.put("email", "timmy00274672@gmail.com");
		properties.put("category", "food");
		properties.put("amount", "2100");
		formService.submitStartFormData(processDefId, properties);

		String taskId = activitiRule.getTaskService().createTaskQuery()
				.singleResult().getId();
		properties.clear();
		properties.put("isApproved", "false");
		properties.put("messege", "what did you eat?");
		properties.put("mailFrom", "timmy00274672@hotmail.com");
		formService.submitTaskFormData(taskId, properties);
	}
}
