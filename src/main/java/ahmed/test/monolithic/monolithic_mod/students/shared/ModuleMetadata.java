package ahmed.test.monolithic.monolithic_mod.students.shared;

import org.springframework.modulith.ApplicationModule;
import org.springframework.modulith.NamedInterface;
import org.springframework.modulith.PackageInfo;

@PackageInfo
@NamedInterface("students.shared")
@ApplicationModule(type = ApplicationModule.Type.OPEN)
public class ModuleMetadata {
}