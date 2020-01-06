package de.sanit4u.evd.example.notification.repository.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import de.sanit4u.evd.example.notification.domain.Frequency;

@Component
public class FrequencyWriterConverter implements Converter<Frequency, Integer> {

	@Override
	public Integer convert(Frequency frequency) {
		return frequency.getDays();
	}
}
