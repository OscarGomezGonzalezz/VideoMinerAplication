package youtubeMiner.Youtube.miner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Channel not found")
public class ChannelNotFound extends Exception{
}
