package pl.zajavka;

import org.springframework.stereotype.Service;

@Service
public class InjectedBeanServiceImpl implements InjectedBeanService {
    @Override
    public boolean anotherSampleMethod() {
        System.out.println("InjectedBeanServiceImpl#anotherSampleMethod()");
        return false;
    }
}
