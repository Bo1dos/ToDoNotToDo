package com.task.manager;

import java.util.UUID;


import com.task.manager.context.UserContext;
import com.task.manager.context.UserContextImpl;
import com.task.manager.contoller.AuthController;
import com.task.manager.contoller.TaskController;
import com.task.manager.contoller.UserController;
import com.task.manager.domain.dto.TaskDTO;
import com.task.manager.domain.dto.UserDTO;
import com.task.manager.domain.mapper.EntityMapper;
import com.task.manager.domain.mapper.TaskMapper;
import com.task.manager.domain.mapper.UserMapper;
import com.task.manager.domain.model.Task;
import com.task.manager.domain.model.User;
import com.task.manager.infrastructure.ConsoleNotifier;
import com.task.manager.infrastructure.MessageBundleProvider;
import com.task.manager.infrastructure.Notifier;
import com.task.manager.infrastructure.command.CommandDispatcher;
import com.task.manager.infrastructure.command.CommandExecutor;
import com.task.manager.infrastructure.command.authcommand.LoginCommand;
import com.task.manager.infrastructure.command.authcommand.LogoutCommand;
import com.task.manager.infrastructure.command.authcommand.RegistrationCommand;
import com.task.manager.infrastructure.command.interfacecommand.ClearCommand;
import com.task.manager.infrastructure.command.interfacecommand.ExitCommand;
import com.task.manager.infrastructure.command.interfacecommand.HelpInfoCommand;
import com.task.manager.infrastructure.command.interfacecommand.SetLocaleCommand;
import com.task.manager.infrastructure.command.interfacecommand.WhoIAmCommand;
import com.task.manager.infrastructure.command.taskcommand.CreateTaskCommand;
import com.task.manager.infrastructure.command.taskcommand.DeleteTaskCommand;
import com.task.manager.infrastructure.command.taskcommand.FindAllTasksCommand;
import com.task.manager.infrastructure.command.taskcommand.FindDoneTasksCommand;
import com.task.manager.infrastructure.command.taskcommand.FindOverdueTasksCommand;
import com.task.manager.infrastructure.command.taskcommand.FindTaskByIdCommand;
import com.task.manager.infrastructure.command.taskcommand.FindTasksByHeaderCommand;
import com.task.manager.infrastructure.command.taskcommand.FindUndoneTasksCommand;
import com.task.manager.infrastructure.command.taskcommand.UpdateTaskCommand;
import com.task.manager.infrastructure.event.EventFactory;
import com.task.manager.infrastructure.notification.DefaultNotificationService;
import com.task.manager.infrastructure.notification.NotificationService;
import com.task.manager.repository.TaskRepository;
import com.task.manager.repository.TaskRepositoryHibernate;
import com.task.manager.repository.UserRepository;
import com.task.manager.repository.UserRepositoryHibernate;
import com.task.manager.service.AuthService;
import com.task.manager.service.AuthServiceImpl;
import com.task.manager.service.UserService;
import com.task.manager.service.UserServiceImpl;
import com.task.manager.utils.StringUtils;
import com.task.manager.service.TaskService;
import com.task.manager.service.TaskServiceImpl;
import com.task.manager.view.ui.UserInterface;
import com.task.manager.view.ui.cli.CliUserInterfaceImpl;

public class TaskManagerApp {
    public static void main(String[] args) {
        // Init UserContext
        UserContext userContext = new UserContextImpl();

        // Init repositories
        TaskRepository taskRepo = new TaskRepositoryHibernate();
        UserRepository userRepo = new UserRepositoryHibernate();

        // Init mappers
        EntityMapper<Task, TaskDTO> taskMapper = new TaskMapper(userContext);
        EntityMapper<User, UserDTO> userMapper = new UserMapper(userContext);

        // Init services
        TaskService taskService = new TaskServiceImpl(taskRepo, taskMapper, userContext);
        UserService userService = new UserServiceImpl(userRepo, userMapper, userContext);
        AuthService authService = new AuthServiceImpl(userRepo, userContext, userService);

        // Init infrastructure
        Notifier notifier = new ConsoleNotifier();
        NotificationService notificationService = new DefaultNotificationService(notifier);
        MessageBundleProvider bundleProvider = new MessageBundleProvider();
        EventFactory eventFactory = new EventFactory(bundleProvider);

        // Init controllers
        TaskController taskController = new TaskController(taskService, taskMapper, notificationService, eventFactory);
        UserController userController = new UserController(userService, userMapper, notificationService, eventFactory);
        AuthController authController = new AuthController(authService, notificationService, eventFactory);

        // Init dispatcher, register commands
        CommandExecutor commandExecutor = new CommandExecutor(notificationService, eventFactory);
        CommandDispatcher dispatcher = new CommandDispatcher(commandExecutor);
        register(dispatcher, authController, taskController, bundleProvider, userContext, userMapper);


        // Init and run UI
        UserInterface ui = new CliUserInterfaceImpl(dispatcher);
        ui.run();
    }

    private static void register(CommandDispatcher dispatcher, AuthController authController, 
                            TaskController taskController, MessageBundleProvider bundleProvider, 
                            UserContext userContext, EntityMapper userMapper) {
        dispatcher.register("register", args ->
            new RegistrationCommand(new UserDTO((String) args.get("username"), 
                        ((String) args.get("password")).toCharArray()), authController));

        dispatcher.register("login", args ->
            new LoginCommand(new UserDTO((String) args.get("username"), ((String) args.get("password")).toCharArray()), authController));

        dispatcher.register("logout", args ->
            new LogoutCommand(authController));

        dispatcher.register("create-task", args ->
            new CreateTaskCommand(TaskDTO.builder()
                .header((String) args.get("header"))
                .description((String) args.get("desc"))
                .deadLine(StringUtils.toInstant((String) args.get("deadline"))) // null-safe
                .build(), taskController));

        dispatcher.register("update-task", args ->
            new UpdateTaskCommand(TaskDTO.builder()
                .id(UUID.fromString((String) args.get("id")))
                .header((String) args.get("header"))
                .description((String) args.get("desc"))
                .deadLine(StringUtils.toInstant((String) args.get("deadline")))
                .build(), taskController));

        dispatcher.register("delete-task", args ->
            new DeleteTaskCommand(UUID.fromString((String) args.get("id")), taskController));

        dispatcher.register("show-task", args ->
            new FindTaskByIdCommand(UUID.fromString((String) args.get("id")), taskController));

        dispatcher.register("find-task-by-header", args ->
            new FindTasksByHeaderCommand((String) args.get("header"), taskController));

        dispatcher.register("list-tasks", args -> new FindAllTasksCommand(taskController));
        dispatcher.register("list-done", args -> new FindDoneTasksCommand(taskController));
        dispatcher.register("list-undone", args -> new FindUndoneTasksCommand(taskController));
        dispatcher.register("list-overdue", args -> new FindOverdueTasksCommand(taskController));

        dispatcher.register("set-language", args -> new SetLocaleCommand(bundleProvider, (String) args.get("lang")));
        dispatcher.register("help", args -> new HelpInfoCommand(bundleProvider));

        dispatcher.register("clear", args -> new ClearCommand());
        dispatcher.register("exit", args -> new ExitCommand(() -> System.exit(0)));
        dispatcher.register("whoami", args -> new WhoIAmCommand(userContext, userMapper));


    }
}