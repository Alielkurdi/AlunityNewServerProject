package io.alunity.content.referral;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.collect.Lists;
import io.alunity.Server;
import io.alunity.model.items.GameItem;
import io.alunity.model.items.NamedItem;
import io.alunity.util.ItemConstants;

public class ReferralCode {

    private static final List<ReferralCode> REFERRAL_CODES = Lists.newArrayList();

    public static void load() throws IOException {
        ItemConstants itemConstants = new ItemConstants().load();
        List<ReferralCode> list = new ObjectMapper(new YAMLFactory()).readValue(new File(Server.getDataDirectory() + "/cfg/referral_codes.yaml"), new TypeReference<List<ReferralCode>>() {});
        list.forEach(it -> {
            it.rewardsList = it.rewards.stream().map(item -> item.toGameItem(itemConstants)).collect(Collectors.toList());
        });
        REFERRAL_CODES.clear();
        REFERRAL_CODES.addAll(list);
    }

    public static List<ReferralCode> getReferralCodes() {
        return Collections.unmodifiableList(REFERRAL_CODES);
    }

    private String code;
    private List<NamedItem> rewards;
    private List<GameItem> rewardsList;

    public ReferralCode() {
        code = "";
        rewards = Collections.emptyList();
    }

    public String getCode() {
        return code;
    }

    public List<GameItem> getRewards() {
        return rewardsList;
    }
}
